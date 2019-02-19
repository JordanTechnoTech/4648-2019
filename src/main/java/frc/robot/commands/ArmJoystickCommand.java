package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */
public class ArmJoystickCommand extends Command {
    public ArmJoystickCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(RobotMap.armSubsystem);
        SmartDashboard.putData("Manual Arm Control", this);
    }

    // Called repeatedly when this Command is scheduled to run
    public void log() {

    }

    @Override
    protected void execute() {
        double leftY, rightY, leftX;
        leftY = OI.deadZone(Robot.m_oi.controller1.getStickLeftYValue(), RobotMap.getTranslationaldeadzone());
        leftX = OI.deadZone(Robot.m_oi.controller1.getStickLeftXValue(), RobotMap.getTranslationaldeadzone());
        rightY = OI.deadZone(Robot.m_oi.controller1.getStickRightYValue(), RobotMap.getTranslationaldeadzone());

        RobotMap.armSubsystem.moveElbowPower(leftY * .5);
        RobotMap.armSubsystem.moveShoulderPower(rightY * .5);
        RobotMap.armSubsystem.moveWristPower(leftX * .5);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }
}