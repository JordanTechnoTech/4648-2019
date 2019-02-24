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
            double leftX;
            leftX = OI.deadZone(Robot.m_oi.controller1.getStickLeftXValue(), .15);

            if (leftX == 0.0) {
                RobotMap.wristSubsystem.stopWrist();
            } else {
                RobotMap.wristSubsystem.moveWristPower(leftX * .5);
            }
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
