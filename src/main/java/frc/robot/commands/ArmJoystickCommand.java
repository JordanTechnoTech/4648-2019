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

    private boolean stoppedElbow = true;
    private boolean stoppedShoulder;

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void execute() {
        SmartDashboard.putString("Arm mode", "Joystick");
        double leftY, rightY, leftX;
        leftY = OI.deadZone(Robot.m_oi.controller1.getStickLeftYValue(), .15);
        leftX = OI.deadZone(Robot.m_oi.controller1.getStickLeftXValue(), .15);
        rightY = OI.deadZone(Robot.m_oi.controller1.getStickRightYValue(), .15);

        if (Math.abs(leftY) < .15 && !stoppedElbow) {
            stoppedElbow = true;
            RobotMap.armSubsystem.stopElbow();
        } else if (Math.abs(leftY) > .15) {
            stoppedElbow = false;
            RobotMap.armSubsystem.moveElbowPower(leftY * .5);
        }

        if (Math.abs(rightY) < .25 && !stoppedShoulder) {
            RobotMap.armSubsystem.stopShoulder();
        } else if (Math.abs(rightY) > .25) {
            RobotMap.armSubsystem.moveShoulderPower(rightY * .5);
        }
        RobotMap.armSubsystem.moveWristPower(leftX * 1);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }
}