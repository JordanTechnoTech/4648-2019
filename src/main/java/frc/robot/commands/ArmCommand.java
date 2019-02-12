package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.subsystems.ArmSubsystem;

public class ArmCommand extends Command {
    @Override
    protected void execute() {
        double position = ArmSubsystem.getPosition();
        SmartDashboard.putNumber("WristMotor",.6);
        SmartDashboard.putNumber("Wrist Encoder1", RobotMap.wristCounterEncoder.getValue());
        RobotMap.wristMotorController.set(.6);
        super.execute();
        double wristSpeed = getWristSpeed(position);
    }

    double position;
    public double getWristSpeed(double position){
        double wSpeed = 0;
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
