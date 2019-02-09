package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;

public class CloseoutCommand extends Command {
    double distance = RobotMap.sonar.ultrasonicRange();
    public void initialize(){
        super.initialize();
    }
    public void execute(){
    double forwardSpeed = getForwardSpeed(distance);
    RobotMap.drivetrain.getDrivetrain().driveCartesian(0, forwardSpeed, 0);
}

    private double getForwardSpeed(double distance) {
        double qSetSpeed = 0d;
        if (distance <= 20){
            RobotMap.drivetrain.frontLeft.stopMotor();
            RobotMap.drivetrain.frontRight.stopMotor();
            RobotMap.drivetrain.backLeft.stopMotor();
            RobotMap.drivetrain.backRight.stopMotor();
        } else if (distance <= 40) {
            qSetSpeed = -.15d;
        } else if (distance <= 60) {
            qSetSpeed = -.2d;
        } else if (distance <= 80) {
            qSetSpeed = -.25d;
        } else {
            qSetSpeed = 0d;
        }
        return qSetSpeed;
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
    public synchronized void cancel(){
        super.cancel();
    }
}
