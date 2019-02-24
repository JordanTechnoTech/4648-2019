package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class WristJoystickCommand extends Command {
    public WristJoystickCommand() {
        requires(RobotMap.wristSubsystem);
        SmartDashboard.putData("Manual Wrist Control", this);
    }

    public void log() {

    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void execute() {
        if (!RobotMap.isRunningAutoArm()) {
            SmartDashboard.putString("Wrist mode", "Joystick");
            double leftTrigger, rightTrigger;
            leftTrigger = OI.deadZone(Robot.m_oi.controller1.getLeftTriggerValue(), .15);
            rightTrigger = OI.deadZone(Robot.m_oi.controller1.getRightTriggerValue(), .15);

            if (leftTrigger > 0) {
                RobotMap.wristSubsystem.moveWristPower(leftTrigger * .5);
            } else if (rightTrigger > 0){
                RobotMap.wristSubsystem.moveWristPower(rightTrigger * -.5);
            }
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
