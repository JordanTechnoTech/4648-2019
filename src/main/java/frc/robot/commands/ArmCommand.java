package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.talon.Gains;

public class ArmCommand extends Command {
    private double shoulderPosition;
    private double elbowPosition;
    private double wristPosition;
    private boolean finished = false;
    private Gains elbowGains = new Gains(1.85, 0, 1, 0.0, 0, .5, .2);
    private Gains shoulderGains = new Gains(1.85, 0, 1, 0.0, 0, .2, .2);

    public ArmCommand(double shoulderPosition, double elbowPosition, double wristPosition) {
        this.shoulderPosition = shoulderPosition;
        this.elbowPosition = elbowPosition;
        this.wristPosition = wristPosition;
        requires(RobotMap.shoulderSubsystem);
        requires(RobotMap.elbowSubsystem);
        requires(RobotMap.wristSubsystem);
    }

    int counter = 0;

    @Override
    protected void execute() {
        RobotMap.runningAutoArm(true);
        counter++;
        SmartDashboard.putString("Arm mode", "AUTO:"+counter);
        RobotMap.elbowSubsystem.moveElbowToPosition(elbowPosition, elbowGains);
        RobotMap.shoulderSubsystem.moveShoulderToPosition(shoulderPosition, shoulderGains);
        finished = true;
    }

    @Override
    public synchronized void cancel() {
        SmartDashboard.putString("Arm mode", "AUTO CANCELLED");
        RobotMap.runningAutoArm(false);
        super.cancel();
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }
}
