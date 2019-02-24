package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ShoulderJoystickCommand extends Command {
    public ShoulderJoystickCommand() {
        requires(RobotMap.shoulderSubsystem);
        SmartDashboard.putData("Manual Shoulder Control", this);
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
            SmartDashboard.putString("Shoulder mode", "Joystick");
            double rightY;
            rightY = OI.deadZone(Robot.m_oi.controller1.getStickRightYValue(), .15);
            SmartDashboard.putNumber("Shoulder joystick",rightY);

            if (rightY == 0.0 || (RobotMap.shoulderSubsystem.getShoulderPosition() < 300 && rightY > 0) || (RobotMap.shoulderSubsystem.getShoulderPosition() > 3000 && rightY < 0)) {
                RobotMap.shoulderSubsystem.stopShoulder();
            } else {
                RobotMap.shoulderSubsystem.moveShoulderPower(-rightY * .4);
            }
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
