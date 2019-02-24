package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ArmCommand extends Command {
    private double shoulderPosition;
    private double elbowPosition;
    private double wristPosition;
    private boolean finished = false;

    public ArmCommand(double shoulderPosition, double elbowPosition, double wristPosition) {
        this.shoulderPosition = shoulderPosition;
        this.elbowPosition = elbowPosition;
        this.wristPosition = wristPosition;
        requires(RobotMap.armSubsystem);
    }

    @Override
    protected void execute() {
        RobotMap.runningAutoArm(true);
        SmartDashboard.putString("Arm mode","AUTO");
        RobotMap.armSubsystem.moveShoulder(shoulderPosition);
        RobotMap.armSubsystem.moveElbow(elbowPosition);
        finished = true;
    }

    @Override
    public synchronized void cancel() {
        RobotMap.runningAutoArm(false);
        super.cancel();
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }
}
