package frc.robot.subsystems;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ElbowJoystickCommand;
import frc.robot.talon.Constants;
import frc.robot.talon.Gains;
import frc.robot.talon.TalonInitializer;

public class ElbowSubsystem extends Subsystem implements TechnoTechSubsystem {
    private WPI_TalonSRX elbow;
    private Gains defaultKGains = new Gains(1.85, 0, 1, 0.0, 0, .2,.2 );
    private boolean stopped= false;

    public ElbowSubsystem(int elbowCanId) {
        this.elbow = new WPI_TalonSRX(elbowCanId);
    }

    public void resetTalonEncoder() {
        elbow.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        moveElbowToPosition(300, defaultKGains);
    }

    @Override
    public void log() {
        SmartDashboard.putNumber("Elbow sensor value", elbow.getSelectedSensorPosition());
        SmartDashboard.putNumber("Elbow motor output", elbow.getMotorOutputPercent());
        SmartDashboard.putNumber("Elbow motor current", elbow.getOutputCurrent());
    }

    public void moveElbowToPosition(double position, Gains kGains) {
        this.elbow.setNeutralMode(NeutralMode.Coast);
        TalonInitializer.initTalon(this.elbow, kGains);
        this.elbow.set(ControlMode.Position, position);
    }
    int stopCounter = 0;
    public void stopElbow() {
        if (!this.stopped) {
            this.stopped = true;
            stopCounter++;
            this.elbow.set(0);
            this.elbow.setNeutralMode(NeutralMode.Brake);
            SmartDashboard.putString("ElbowSubsystem running status", "stopped:"+stopCounter);
            // this.moveElbowToPosition(this.elbow.getSelectedSensorPosition(), defaultKGains);
            // this.elbow.stopMotor();
        }
    }

    public void moveElbowPower(double power) {
        stopped = false;
        SmartDashboard.putString("ElbowSubsystem running status","running");
        ErrorCode errorCode = this.elbow.configPeakOutputForward(.5, Constants.kTimeoutMs);
        ErrorCode errorCode1 = this.elbow.configPeakOutputReverse(-.5, Constants.kTimeoutMs);
        SmartDashboard.putString("ElbowSubsystem errCode",""+errorCode.name()+":"+errorCode.value);
        SmartDashboard.putString("ElbowSubsystem errCode1",""+errorCode1.name()+":"+errorCode1.value);
        this.elbow.set(power);
    }

    public int getElbowPosition() {
        return this.elbow.getSelectedSensorPosition();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ElbowJoystickCommand());
    }
}
