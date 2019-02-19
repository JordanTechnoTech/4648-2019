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
public class VacuumJoystickCommand extends Command {
    public VacuumJoystickCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(RobotMap.vacuumSubsystem);
     //   SmartDashboard.putData("VacuumJoystickCommand", this);
    }

    // Called repeatedly when this Command is scheduled to run
    public void log() {
    }

    @Override
    protected void execute() {
        double leftY;
        leftY = OI.deadZone(Robot.m_oi.controller1.getStickLeftYValue(), RobotMap.getTranslationaldeadzone());
        RobotMap.vacuumSubsystem.setVacuumPower(leftY * .5);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }
}