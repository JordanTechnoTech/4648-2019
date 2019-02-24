package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */
public class ElbowJoystickCommand extends Command {
    public ElbowJoystickCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(RobotMap.elbowSubsystem);
        SmartDashboard.putData("Manual Elbow Control", this);
    }

    // Called repeatedly when this Command is scheduled to run
    public void log() {

    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void execute() {
        if (!RobotMap.isRunningAutoArm()) {
            SmartDashboard.putString("Elbow mode", "Joystick");
            double leftY;
            leftY = OI.deadZone(Robot.m_oi.controller1.getStickLeftYValue(), .15);

            if (leftY == 0.0) {
                RobotMap.elbowSubsystem.stopElbow();
            } else {
                RobotMap.elbowSubsystem.moveElbowPower(leftY * .5);
            }
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }
}