package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.talon.Gains;

public class ElbowCommand extends Command {
    private boolean finished = false;

    public ElbowCommand() {
        requires(RobotMap.elbowSubsystem);
        SmartDashboard.putData(this);
        SmartDashboard.putNumber("Elbow Kp", .75);
        SmartDashboard.putNumber("Elbow Ki", 0);
        SmartDashboard.putNumber("Elbow Kd", 1);
        SmartDashboard.putNumber("Elbow kF", 0);
        SmartDashboard.putNumber("Elbow kIzone", 0);
        SmartDashboard.putNumber("Elbow kPeakOutput", .2);
        SmartDashboard.putNumber("Elbow position", 0);
    }

    @Override
    protected void execute() {
        double kp = SmartDashboard.getNumber("Elbow Kp", .75);
        double Ki = SmartDashboard.getNumber("Elbow Ki", 0);
        double Kd = SmartDashboard.getNumber("Elbow Kd", 1);
        double kF = SmartDashboard.getNumber("Elbow kF", 0);
        int kIzone = (int) SmartDashboard.getNumber("Elbow kIzone", 0);
        double kPeakOutput = SmartDashboard.getNumber("Elbow kPeakOutput", .2);
        double dbPosition = SmartDashboard.getNumber("Elbow position", 0);
        Gains gains = new Gains(kp, Ki, Kd, kF, kIzone, kPeakOutput);
        RobotMap.elbowSubsystem.moveElbowToPosition(dbPosition, gains);
        finished = true;
    }

    @Override
    public synchronized void cancel() {
        RobotMap.elbowSubsystem.stopElbow();
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }
}
