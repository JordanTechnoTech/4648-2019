package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */
public class MecanumDriveCommand extends Command {
    public MecanumDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.m_subsystem);
        requires(RobotMap.drivetrain);
    }

    // // Called just before this Command runs the first time
    // @Override
    // protected void initialize() {
    // }

    // Called repeatedly when this Command is scheduled to run
    public void log() {

    }

    @Override
    protected void execute() {
        double x, y, twist;
        x = Robot.m_oi.controller0.deadZone(Robot.m_oi.controller0.getStickLeftXValue(), RobotMap.getTranslationaldeadzone());
        y = Robot.m_oi.controller0.deadZone(Robot.m_oi.controller0.getStickRightYValue(), RobotMap.getTranslationaldeadzone());
        twist = Robot.m_oi.controller0.deadZone(Robot.m_oi.controller0.getStickRightXValue(), RobotMap.getRotationaldeadzone());
        twist = twist * .35;

        RobotMap.drivetrain.getDrivetrain().driveCartesian(-x, y, -twist);
        SmartDashboard.putNumber("Drive twist", twist);
        SmartDashboard.putNumber("Drive x", x);
        SmartDashboard.putNumber("Drive y", y);
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