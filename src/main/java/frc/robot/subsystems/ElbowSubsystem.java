package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.talon.Constants;
import frc.robot.talon.Gains;
import frc.robot.talon.TalonInitializer;

public class ElbowSubsystem extends Subsystem implements TechnoTechSubsystem {
    private WPI_TalonSRX elbow;
    private Gains defaultKGains = new Gains(0.015, 0, 1, 0.0, 0, 1.0);

    public ElbowSubsystem(int elbowCanId) {
        this.elbow = new WPI_TalonSRX(elbowCanId);
    }

    @Override
    protected void initDefaultCommand() {
//        stopElbow();
    }

    public void resetTalonEncoder() {
        elbow.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    }

    @Override
    public void log() {
        SmartDashboard.putNumber("Elbow sensor value", elbow.getSelectedSensorPosition());
        SmartDashboard.putNumber("Elbow motor output", elbow.getMotorOutputPercent());
    }

    public void moveElbowToPosition(double position, Gains kGains) {
        TalonInitializer.initTalon(this.elbow, kGains);
        this.elbow.set(ControlMode.Position, position);
    }

    public void moveElbowPower(double power) {
        this.elbow.set(ControlMode.PercentOutput, power);
    }

    public void stopElbow() {
//        this.moveElbowToPosition(this.elbow.getSelectedSensorPosition(), defaultKGains);
        this.elbow.stopMotor();
    }

    public int getElbowPosition() {
        return this.elbow.getSelectedSensorPosition();
    }
}
