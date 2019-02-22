package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.talon.Constants;
import frc.robot.talon.Gains;
import frc.robot.talon.TalonInitializer;

public class ShoulderSubsystem extends Subsystem implements TechnoTechSubsystem {
    private WPI_TalonSRX shoulder;
    private Gains defaultKGains = new Gains(0.015, 0, 1, 0.0, 0, 1.0);

    public ShoulderSubsystem(int shoulderCanId){
        this.shoulder = new WPI_TalonSRX(shoulderCanId);
        this.shoulder.setInverted(true);
   //     stopShoulder();
    }
    public void resetTalonEncoder() {
        shoulder.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    }

    @Override
    public void log() {
        SmartDashboard.putNumber("Shoulder sensor value", shoulder.getSelectedSensorPosition());
        SmartDashboard.putNumber("Shoulder motor output", shoulder.getMotorOutputPercent());
    }
    public void moveShoulderToPosition(double position, Gains kGains) {
        TalonInitializer.initTalon(this.shoulder, kGains);
        this.shoulder.set(ControlMode.Position, position);
    }
    public void stopShoulder() {
     //   this.moveShoulder(this.shoulder.getSelectedSensorPosition()+600);
        this.shoulder.stopMotor();
    }
    public int getShoulderPosiion() {
        return this.shoulder.getSelectedSensorPosition();
    }

    public void moveShoulderPower(double power) {
        this.shoulder.set(ControlMode.PercentOutput, power);
    }

    @Override
    protected void initDefaultCommand() {

    }
}
