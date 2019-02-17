package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ResetArmCommand extends Command {
    public ResetArmCommand() {
        super();
        requires(RobotMap.armSubsystem);
    }

    public static final int LOWEST_ELBOW_POSITION = 100;
    boolean finished = false;

    @Override
    protected void execute() {
        if(RobotMap.armSubsystem.getElbowPosition() > LOWEST_ELBOW_POSITION){
            RobotMap.armSubsystem.moveElbowPower(-.15);
        } else {
            RobotMap.armSubsystem.stopElbow();
            finished = true;
        }
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }
}
