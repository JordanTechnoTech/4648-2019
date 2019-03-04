package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ElbowJoystickCommand extends Command {
    public ElbowJoystickCommand() {
        requires(RobotMap.elbowSubsystem);
        SmartDashboard.putData("Manual Elbow Control", this);
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
            SmartDashboard.putString("Elbow mode", "Joystick");
            double leftY;
            leftY = OI.deadZone(Robot.m_oi.controller1.getStickLeftYValue(), .15);
            SmartDashboard.putNumber("Elbow joystick",leftY);

            if (leftY == 0.0 || (RobotMap.elbowSubsystem.getElbowPosition() < 300 && leftY > 0) || (RobotMap.elbowSubsystem.getElbowPosition() > 5000 && leftY < 0)) {
                RobotMap.elbowSubsystem.stopElbow();
            } else {
                RobotMap.elbowSubsystem.moveElbowPower(leftY * -.5);
            }
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}