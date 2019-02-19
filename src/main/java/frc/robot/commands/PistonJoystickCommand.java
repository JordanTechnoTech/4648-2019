package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

import java.nio.channels.Channel;

/**
 * An example command.  You can replace me with your own command.
 */
public class PistonJoystickCommand extends Command {
    public PistonJoystickCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(RobotMap.pistonSubsystem);
        SmartDashboard.putData("PistonJoystickCommand", this);
    }

    // Called repeatedly when this Command is scheduled to run
    public void log() {
    }

    @Override
    protected void execute() {
        double rightY;
        rightY = OI.deadZone(Robot.m_oi.controller1.getStickRightYValue(), RobotMap.getTranslationaldeadzone());
        RobotMap.pistonSubsystem.setPistonPower(rightY * .5);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }
}