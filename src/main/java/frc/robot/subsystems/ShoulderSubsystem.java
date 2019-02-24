package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ShoulderJoystickCommand;
import frc.robot.talon.Constants;
import frc.robot.talon.Gains;
import frc.robot.talon.TalonInitializer;

public class ShoulderSubsystem extends Subsystem implements TechnoTechSubsystem {
    private WPI_TalonSRX shoulder;
    private Gains defaultUpKGains = new Gains(1.85, 0, 1, 0.0, 0, .2);
    private Gains defaultDownKGains = new Gains(1.85, 0, 1, 0.0, 0, .2);

    public ShoulderSubsystem(int shoulderCanId) {
        this.shoulder = new WPI_TalonSRX(shoulderCanId);
        this.shoulder.setInverted(true);
    }

    public void resetTalonEncoder() {
        shoulder.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    }

    @Override
    public void log() {
        SmartDashboard.putNumber("Shoulder sensor value", shoulder.getSelectedSensorPosition());
        SmartDashboard.putNumber("Shoulder motor output", shoulder.getMotorOutputPercent());
        SmartDashboard.putNumber("Shoulder motor current", shoulder.getOutputCurrent());
    }

    public void moveShoulderToPosition(double position, Gains kGains) {
        TalonInitializer.initTalon(this.shoulder, kGains);
        this.shoulder.set(ControlMode.Position, position);
    }

    public void stopShoulder() {
        this.shoulder.setNeutralMode(NeutralMode.Brake);
        this.shoulder.stopMotor();
    }

    public void moveShoulderPower(double power) {
        this.shoulder.set(ControlMode.PercentOutput, power);
    }

    public int getShoulderPosiion() {
        return this.shoulder.getSelectedSensorPosition();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ShoulderJoystickCommand());
    }
}
