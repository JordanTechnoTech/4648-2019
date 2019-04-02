package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
//This runs the Elbow when you move the joystick
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
//If the Elbow isn't running a preset then the arm will run based on joystick position
    @Override
    protected void execute() {
        if (!RobotMap.isRunningAutoArm()) {
            SmartDashboard.putString("Elbow mode", "Joystick");
            double leftY;
            leftY = OI.deadZone(Robot.m_oi.controller1.getStickLeftYValue(), .15);
            SmartDashboard.putNumber("Elbow joystick",leftY);
/*
If there is no joystick input
Or the elbow is at less than 300 ticks and the left stick is being pulled down
Or the elbow is above 7000 ticks and being pushed up
The elbow will stop
Otherwise the elbow will move at half the value of the joystick
*/
            if (leftY == 0.0 || (RobotMap.elbowSubsystem.getElbowPosition() < 300 && leftY > 0) || (RobotMap.elbowSubsystem.getElbowPosition() > 7000 && leftY < 0)) {
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