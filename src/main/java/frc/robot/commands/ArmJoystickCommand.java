package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.XBoxButton;

/**
 * An example command.  You can replace me with your own command.
 */
public class ArmJoystickCommand extends Command {
    public ArmJoystickCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(RobotMap.armSubsystem);
        SmartDashboard.putData("Manual Arm Control",this);
    }

    // Called repeatedly when this Command is scheduled to run
    public void log() {

    }
    @Override
    protected void execute() {
        XboxController controller1 = new XboxController(1);
        Button rbButton = new XBoxButton(controller1, XBoxButton.kBumperRight);
        Button lbButton = new XBoxButton(controller1, XBoxButton.kBumperLeft);
        double leftY, rightY, twist;
        leftY = OI.deadZone(Robot.m_oi.getStickLeftYValue(), RobotMap.getTranslationaldeadzone());
        rightY = OI.deadZone(Robot.m_oi.getStickRightYValue(), RobotMap.getTranslationaldeadzone());
        rbButton.whileHeld(new CloseoutCommand());
        lbButton.whileHeld(new CloseoutCommand());
        RobotMap.armSubsystem.moveElbowPower(leftY*.5);
        RobotMap.armSubsystem.moveShoulderPower(rightY*.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // // Called once after isFinished returns true
    // @Override
    // protected void end() {
    // }

    // // Called when another command which requires one or more of the same
    // // subsystems is scheduled to run
    // @Override
    // protected void interrupted() {
    // }
}