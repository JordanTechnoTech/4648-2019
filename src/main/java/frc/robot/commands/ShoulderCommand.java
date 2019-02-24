package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.talon.Gains;

public class ShoulderCommand extends Command {
    private boolean finished = false;

    public ShoulderCommand() {
        requires(RobotMap.shoulderSubsystem);
        SmartDashboard.putData(this);
        SmartDashboard.putNumber("Shoulder Kp", 1.85);
        SmartDashboard.putNumber("Shoulder Ki", 0);
        SmartDashboard.putNumber("Shoulder Kd", 1);
        SmartDashboard.putNumber("Shoulder kF", 0);
        SmartDashboard.putNumber("Shoulder kIzone", 0);
        SmartDashboard.putNumber("Shoulder kPeakOutput", .2);
        SmartDashboard.putNumber("Shoulder position", 0);
    }

    @Override
    protected void execute() {
        RobotMap.runningAutoArm(true);
        double kp = SmartDashboard.getNumber("Shoulder Kp", 1.85);
        double Ki = SmartDashboard.getNumber("Shoulder Ki", 0);
        double Kd = SmartDashboard.getNumber("Shoulder Kd", 1);
        double kF = SmartDashboard.getNumber("Shoulder kF", 0);
        int kIzone = (int) SmartDashboard.getNumber("Shoulder kIzone", 0);
        double kPeakOutput = SmartDashboard.getNumber("Shoulder kPeakOutput", .2);
        double dbPosition = SmartDashboard.getNumber("Shoulder position", 0);
        Gains gains = new Gains(kp, Ki, Kd, kF, kIzone, kPeakOutput);
        RobotMap.shoulderSubsystem.moveShoulderToPosition(dbPosition, gains);
        finished = true;
    }

    @Override
    public synchronized void cancel() {
        RobotMap.runningAutoArm(false);
        RobotMap.elbowSubsystem.stopElbow();
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }
}
