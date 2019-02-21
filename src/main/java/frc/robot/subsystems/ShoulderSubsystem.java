package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.talon.Gains;
import frc.robot.talon.TalonInitializer;

public class ShoulderSubsystem extends Subsystem implements TechnoTechSubsystem {
    private WPI_TalonSRX shoulder;
    public ShoulderSubsystem(int shoulderCanId){
        this.shoulder = new WPI_TalonSRX(shoulderCanId);
        this.shoulder.setInverted(true);
    }
    @Override
    protected void initDefaultCommand() {
        Gains kGains1 = new Gains(0.23, 0, 1, 0.0, 0, 1.0);
        TalonInitializer.initTalon(this.shoulder, kGains1);
    }

    @Override
    public void log() {
        SmartDashboard.putNumber("Shoulder sensor value", shoulder.getSelectedSensorPosition());
        SmartDashboard.putNumber("Shoulder motor output", shoulder.getMotorOutputPercent());
    }
    public void moveShoulder(double position) {
        this.shoulder.set(ControlMode.Position, position);
    }
    public void stopShoulder() {
        this.moveShoulder(this.shoulder.getSelectedSensorPosition()+600);
    }
    public int getShoulderPosiion() {
        return this.shoulder.getSelectedSensorPosition();
    }
    
    public void moveShoulderPower(double power) {
        this.shoulder.set(ControlMode.PercentOutput, power);
    }

}
