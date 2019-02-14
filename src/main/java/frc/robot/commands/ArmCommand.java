package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.ArmSubsystem;

public class ArmCommand extends Command {
    public ArmCommand(){
        requires(Robot.m_subsystem);
        requires(RobotMap.armSubsystem);
    }

    private void requires(ArmSubsystem armSubsystem) {

    }

    public void log(){

    }
    @Override
    protected void execute() {
        double shoulder, elbow, wrist;
        //shoulderTalon.set(getShoulderSpeed)
        //elbowTalon.set(getElbowSpeed)
        //wristTalon.set(getWristSpeed)


    }
    public double getShoulderSpeed(){
        double shoulderSetSpeed = 0;
        return shoulderSetSpeed;
    }
    public double getElbowSpeed(){
        double elbowSetSpeed = 0;
        return elbowSetSpeed;
    }
    public double getWristSpeed(){
        double wristSetSpeed = 0;
        return wristSetSpeed;
    }



    @Override
    public synchronized void cancel() {
        super.cancel();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
