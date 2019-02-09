package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.camera.LimelightCamera;

public class CloseoutCommand extends Command {

    public void initialize() {
        super.initialize();
    }

    public void execute() {
        double distance = RobotMap.sonar.ultrasonicRange() * 2.54;
        log();
        double forwardSpeed = getForwardSpeed(distance);
        if(forwardSpeed == 0.0){
            RobotMap.drivetrain.frontLeft.stopMotor();
            RobotMap.drivetrain.frontRight.stopMotor();
            RobotMap.drivetrain.backLeft.stopMotor();
            RobotMap.drivetrain.backRight.stopMotor();
        }else {
            RobotMap.drivetrain.getDrivetrain().driveCartesian(0, forwardSpeed, 0);
        }
    }

    public double getForwardSpeed(double distance) {
        double qSetSpeed;
        if (distance <= 20) {
            qSetSpeed = 0;
        } else if (distance <= 40) {
            qSetSpeed = -.15d;
        } else if (distance <= 60) {
            qSetSpeed = -.2d;
        } else if (distance <= 80) {
            qSetSpeed = -.25d;
        } else {
            qSetSpeed = -.4d;
        }
        return qSetSpeed;
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    public synchronized void cancel() {
        super.cancel();
    }

    public void log() {

        LimelightCamera.log();
    }
}
