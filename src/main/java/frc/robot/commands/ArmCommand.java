package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
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
        RobotMap.armSubsystem.moveShoulder(shoulderPosition);
        RobotMap.armSubsystem.moveElbow(elbowPosition);
        double currentPosition = RobotMap.armSubsystem.getStoredWristPosition();
        if (currentPosition > wristPosition - 1) {
            RobotMap.armSubsystem.moveWristPower(-.5);
        } else if (currentPosition < wristPosition + 1) {
            RobotMap.armSubsystem.moveWristPower(.5);
        } else {
            RobotMap.armSubsystem.moveWristPower(0);
            finished = true;
        }
        RobotMap.armSubsystem.moveWristPower(wristPosition);

    }

    @Override
    protected boolean isFinished() {
        return finished;
    }
}
