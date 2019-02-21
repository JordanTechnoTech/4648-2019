package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.subsystems.ShoulderSubsystem;

public class ShoulderCommand extends Command {
    private double shoulderPosition;
    private boolean finished = false;

    public ShoulderCommand(double shoulderPosition){
        this.shoulderPosition = shoulderPosition;
        requires(RobotMap.shoulderSubsystem);
    }

    @Override
    protected void execute() {
        SmartDashboard.putString("Shoulder mode", "Auto");
        RobotMap.shoulderSubsystem.moveShoulder(shoulderPosition);
        finished = true;
    }

    @Override
    protected boolean isFinished() { return finished;}
}
