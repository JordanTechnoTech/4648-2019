package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
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
        SmartDashboard.putNumber("Gyro-Angle", RobotMap.imu.getAngle());
        SmartDashboard.putNumber("Gyro-X", RobotMap.imu.getAngleX());
        SmartDashboard.putNumber("Gyro-Y", RobotMap.imu.getAngleY());
        SmartDashboard.putNumber("Gyro-Z", RobotMap.imu.getAngleZ());
        SmartDashboard.putNumber("Accel-X", RobotMap.imu.getAccelX());
        SmartDashboard.putNumber("Accel-Y", RobotMap.imu.getAccelY());
        SmartDashboard.putNumber("Accel-Z", RobotMap.imu.getAccelZ());
    }
    @Override
    protected void execute() {
        double x, y, twist;
        x = OI.deadZone(Robot.m_oi.getStickLeftXValue(), RobotMap.getTranslationaldeadzone());
        y = OI.deadZone(Robot.m_oi.getStickRightYValue(), RobotMap.getTranslationaldeadzone());
        twist = OI.deadZone(Robot.m_oi.getStickRightXValue(), RobotMap.getRotationaldeadzone());

        RobotMap.drivetrain.getDrivetrain().driveCartesian(-x, y, -twist);
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