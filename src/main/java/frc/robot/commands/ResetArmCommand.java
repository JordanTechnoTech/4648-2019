package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;


public class ResetArmCommand extends Command {
    private double shoulderPosition;
    private double elbowPosition;
    private double wristPosition;

    public ResetArmCommand(double shoulderPosition, double elbowPosition, double wristPosition) {
        this.shoulderPosition = shoulderPosition;
        this.elbowPosition = elbowPosition;
        this.wristPosition = wristPosition;
        requires(RobotMap.armSubsystem);
    }

    public static final int LOWEST_ELBOW_POSITION = 200;
    public static final int LOWEST_SHOULDER_POSITION = 200;
    boolean finished = false;

    @Override
    protected void execute() {
        SmartDashboard.putNumber("Elbow position",RobotMap.armSubsystem.getElbowPosition());
        RobotMap.armSubsystem.moveElbow(0);
        if(RobotMap.armSubsystem.getElbowPosition()< LOWEST_ELBOW_POSITION)
            finished = true;
       /* RobotMap.armSubsystem.moveShoulder(0);
        if(RobotMap.armSubsystem.getShoulderPosition()< LOWEST_SHOULDER_POSITION)
            finished = true;*/
    }

    @Override
    protected boolean isFinished() {
        if(finished){
            ArmCommand command = new ArmCommand(shoulderPosition,elbowPosition,wristPosition );
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
