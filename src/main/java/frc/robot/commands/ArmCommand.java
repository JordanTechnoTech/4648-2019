package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class ArmCommand extends Command {
    @Override
    protected void execute() {
        SmartDashboard.putNumber("WristMotor",.6);
        RobotMap.wristMotorController.set(.6);
        super.execute();
    }

    @Override
    public synchronized void cancel() {
        SmartDashboard.putNumber("WristMotor",0);
        RobotMap.wristMotorController.set(0);
        super.cancel();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
