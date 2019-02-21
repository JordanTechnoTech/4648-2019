package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class ElbowCommand extends Command {
    private double elbowPosition;
    private boolean finished = false;

    public ElbowCommand(double elbowPosition){
        this.elbowPosition = elbowPosition;
        requires(RobotMap.elbowSubsystem);
    }

    @Override
    protected void execute() {
        SmartDashboard.putString("Elbow mode","AUTO");
        RobotMap.elbowSubsystem.moveElbow(elbowPosition);
        finished = true;
    }

    @Override
    protected boolean isFinished() { return finished;}
}
