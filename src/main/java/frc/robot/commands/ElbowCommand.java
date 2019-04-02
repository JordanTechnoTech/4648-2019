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
//This sets the PID tuning
//Kp is Power and the Peak output is the max that the power can go to
//Don't touch the Ki, just don't
//Kd is good at 1 I think
    @Override
    protected void execute() {
        RobotMap.runningAutoArm(true);
        double kp = SmartDashboard.getNumber("Elbow Kp", 1.85);
        double Ki = SmartDashboard.getNumber("Elbow Ki", 0);
        double Kd = SmartDashboard.getNumber("Elbow Kd", 1);
        double kF = SmartDashboard.getNumber("Elbow kF", 0);
        int kIzone = (int) SmartDashboard.getNumber("Elbow kIzone", 0);
        double kPeakOutput = SmartDashboard.getNumber("Elbow kPeakOutput", .2);
        double dbPosition = SmartDashboard.getNumber("Elbow position", 0);
        Gains gains = new Gains(kp, Ki, Kd, kF, kIzone, kPeakOutput,kPeakOutput );
        RobotMap.elbowSubsystem.moveElbowToPosition(dbPosition, gains);
        finished = true;
    }
//Stops the Elbow when cancel is called
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
