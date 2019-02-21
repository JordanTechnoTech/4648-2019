package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.talon.Gains;
import frc.robot.talon.TalonInitializer;

public class ElbowSubsystem extends Subsystem implements TechnoTechSubsystem  {
    private WPI_TalonSRX elbow;
    public ElbowSubsystem(int elbowCanId){
        this.elbow = new WPI_TalonSRX(elbowCanId);
    }

    @Override
    protected void initDefaultCommand() {
        Gains kGains2 = new Gains(0.35, 0, 1, 0.0, 0, 1.0);
        TalonInitializer.initTalon(this.elbow, kGains2);
        stopElbow();
    }

    @Override
    public void log() {

    }
}
