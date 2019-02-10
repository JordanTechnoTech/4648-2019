package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.camera.LimelightCamera;

public class CloseoutCommand extends Command {

    private boolean finished = false;

    public void initialize() {
        LimelightCamera.setLightMode(LimelightCamera.ledMode.OFF);
        LimelightCamera.setPipeline(1);
        LimelightCamera.setCameraMode(LimelightCamera.cameraMode.CAMERA);
        finished = false;
        super.initialize();
    }

    public void execute() {
        double distance = RobotMap.sonar.getRangeCentimeters();
        log();
        double forwardSpeed = getForwardSpeed(distance);
        SmartDashboard.putNumber("Closeout forwardSpeed", forwardSpeed);
        SmartDashboard.putNumber("Closeout distance", distance);
        if (forwardSpeed == 0.0) {
            RobotMap.drivetrain.getDrivetrain().stopMotor();
            finished = true;
        } else {
            RobotMap.drivetrain.getDrivetrain().driveCartesian(0, forwardSpeed, 0);
        }
    }

    public double getForwardSpeed(double distance) {
        double qSetSpeed;
        if (distance <= 10) {
            qSetSpeed = .25d;
        } else if (distance <= 20) {
            qSetSpeed = 0;
        } else if (distance <= 30) {
            qSetSpeed = -.1d;
        } else {
            qSetSpeed = -.25d;
        }
        return qSetSpeed;
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }

    public synchronized void cancel() {
        super.cancel();
    }

    public void log() {

    }
}
