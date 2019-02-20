package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;


public class ResetArmCommand extends Command {
    private static final int MAX_SHOULDER_POSITION = 1300;
    private double shoulderPosition;
    private double elbowPosition;
    private double wristPosition;

    public ResetArmCommand(double shoulderPosition, double elbowPosition, double wristPosition) {
        this.shoulderPosition = Math.min(shoulderPosition,MAX_SHOULDER_POSITION);
        this.elbowPosition = elbowPosition;
        this.wristPosition = wristPosition;
        requires(RobotMap.armSubsystem);
    }

    boolean finished = false;

    @Override
    protected void execute() {
        SmartDashboard.putNumber("Elbow position", RobotMap.armSubsystem.getElbowPosition());
        RobotMap.armSubsystem.moveElbow(300);
        RobotMap.armSubsystem.moveShoulder(100);
//        double minElbowPosi
        if (RobotMap.armSubsystem.getElbowPosition() < elbowPosition && RobotMap.armSubsystem.getShoulderPosiion() < shoulderPosition )
            finished = true;
       /* RobotMap.armSubsystem.moveShoulder(0);
        if(RobotMap.armSubsystem.getShoulderPosition()< LOWEST_SHOULDER_POSITION)
            finished = true;*/
    }

    @Override
    protected boolean isFinished() {
        if (finished) {
            ArmCommand command = new ArmCommand(shoulderPosition, elbowPosition, wristPosition);
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
