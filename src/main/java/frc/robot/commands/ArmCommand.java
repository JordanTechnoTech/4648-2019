package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.subsystems.ArmSubsystem;

public class ArmCommand extends Command {
    @Override
    protected void execute() {
        boolean position = ArmSubsystem.getPosition();
        SmartDashboard.putNumber("WristMotor",-1);
        RobotMap.wristMotorController.set(-1);
        super.execute();
        boolean wristSpeed = getWristSpeed(position);
    }

    double position;
    public boolean getWristSpeed(boolean position){
        boolean wSpeed = false;
        return wSpeed;
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
