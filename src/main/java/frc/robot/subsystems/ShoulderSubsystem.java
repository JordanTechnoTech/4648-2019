package frc.robot.subsystems;

import com.ctre.phoenix.ErrorCode;
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
    private Gains defaultKGains = new Gains(1.85, 0, 1, 0.0, 0, .2,.2 );
    private boolean stopped= false;

    public ShoulderSubsystem(int shoulderCanId) {
        this.shoulder = new WPI_TalonSRX(shoulderCanId);
    }

    public void resetTalonEncoder() {
        shoulder.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        moveShoulderToPosition(300,defaultKGains);
    }

    @Override
    public void log() {
        SmartDashboard.putNumber("Shoulder sensor value", shoulder.getSelectedSensorPosition());
        SmartDashboard.putNumber("Shoulder motor output", shoulder.getMotorOutputPercent());
        SmartDashboard.putNumber("Shoulder motor current", shoulder.getOutputCurrent());
    }

    public void moveShoulderToPosition(double position, Gains kGains) {
        this.shoulder.setNeutralMode(NeutralMode.Coast);
//        stopped = false;
        TalonInitializer.initTalon(this.shoulder, kGains);
        this.shoulder.set(ControlMode.Position, position);
    }
    int stopCounter = 0;
    public void stopShoulder() {
        if (!this.stopped) {
            this.stopped = true;
            stopCounter++;
            this.shoulder.set(0.0);
            this.shoulder.setNeutralMode(NeutralMode.Brake);
//            this.shoulder.stopMotor();
            SmartDashboard.putString("ShoulderSubsystem running status","stopped:"+stopCounter);
//            TalonInitializer.initTalon(this.shoulder, defaultKGains);
//            this.shoulder.set(ControlMode.Position, getShoulderPosition());
        }
    }

    public void moveShoulderPower(double power) {
        stopped = false;
        SmartDashboard.putString("ShoulderSubsystem running status","running");
        ErrorCode errorCode = this.shoulder.configPeakOutputForward(.5, Constants.kTimeoutMs);
        ErrorCode errorCode1 = this.shoulder.configPeakOutputReverse(-.5, Constants.kTimeoutMs);
        SmartDashboard.putString("ShoulderSubsystem errCode",""+errorCode.name()+":"+errorCode.value);
        SmartDashboard.putString("ShoulderSubsystem errCode1",""+errorCode1.name()+":"+errorCode1.value);
        this.shoulder.set(power);
    }

    public int getShoulderPosition() {
        return this.shoulder.getSelectedSensorPosition();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ShoulderJoystickCommand());
    }
}
