package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.camera.LimelightCamera;
//The Sonar Mode
public class CloseoutCommand extends Command {

    private boolean finished = false;
//Turns off the limelight camera and makes it so you can see out of it
    public void initialize() {
        LimelightCamera.setLightMode(LimelightCamera.ledMode.OFF);
        LimelightCamera.setPipeline(1);
        LimelightCamera.setCameraMode(LimelightCamera.cameraMode.CAMERA);
        finished = false;
        super.initialize();
    }
//Takes the distance and plugs it into getForwardSpeed below
//Then it finishes the command when getFowardSpeed returns a speed of 0
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
//This takes in the sonar distance and sets speed based on that
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
