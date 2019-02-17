package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

import java.awt.image.AreaAveragingScaleFilter;

public class ResetArmCommand extends Command {
    private double shoulderPosition;
    private double elbowPosition;

    public ResetArmCommand(double shoulderPosition, double elbowPosition, double wristPosition) {
        this.shoulderPosition = shoulderPosition;
        this.elbowPosition = elbowPosition;
        requires(RobotMap.armSubsystem);
    }

    public static final int LOWEST_ELBOW_POSITION = 200;
    boolean finished = false;

    @Override
    protected void execute() {
        SmartDashboard.putNumber("Elbow position",RobotMap.armSubsystem.getElbowPosition());
        RobotMap.armSubsystem.moveElbow(0);
        if(RobotMap.armSubsystem.getElbowPosition()< LOWEST_ELBOW_POSITION)
            finished = true;
    }

    @Override
    protected boolean isFinished() {
        if(finished){
            ArmCommand command = new ArmCommand(shoulderPosition,elbowPosition,0);
            command.start();
            finished = false;
            return true;
        }
        return finished;
    }

    @Override
    protected void end() {

    }
}
