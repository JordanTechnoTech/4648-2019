package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.talon.Gains;
import frc.robot.talon.TalonInitializer;

public class ArmSubsystem extends Subsystem implements TechnoTechSubsystem {
    private WPI_TalonSRX shoulder, elbow;
    private Talon wrist;
    private Counter wristEncoder;

    public ArmSubsystem(int shoulderCanId, int elbowCanId, int wrist ,int wristEncoderChannel) {//TODO add args for all motors tied to the arm
        this.shoulder = new WPI_TalonSRX(shoulderCanId);
        this.shoulder.setInverted(true);
        this.elbow = new WPI_TalonSRX(elbowCanId);
        this.wrist = new Talon(wrist);
        this.wristEncoder = new Counter(new AnalogTrigger(wristEncoderChannel));
    }

    public void initSubSystem(){
        Gains kGains = new Gains(0.23, 0, 1, 0.0, 0, 1.0);
        TalonInitializer.initTalon(this.shoulder, kGains);
        TalonInitializer.initTalon(this.elbow, kGains);
    }

    @Override
    public void log() {
        SmartDashboard.putNumber("Elbow sensor value", elbow.getSelectedSensorPosition());
        SmartDashboard.putNumber("Shoulder sensor value", shoulder.getSelectedSensorPosition());
        SmartDashboard.putNumber("Shoulder motor output", shoulder.getMotorOutputPercent());
        SmartDashboard.putNumber("Elbow motor output", elbow.getMotorOutputPercent());
    }

    public void moveWrist(double speed){
        //wristTalon.set(speed);
    }

    public void moveElbow(double position){
        this.elbow.set(ControlMode.Position, position);
    }

    public void moveElbowPower(double power){
        this.elbow.set(ControlMode.PercentOutput, power);
    }

    public void moveShoulder(double position){
        this.shoulder.set(ControlMode.Position, position);
    }

    public void stopElbow() {
        this.elbow.stopMotor();
    }

    public void stopShoulder() {
        this.shoulder.stopMotor();
    }

    public int getElbowPosition() {
        return this.elbow.getSelectedSensorPosition();
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void moveShoulderPower(double power) {
        this.shoulder.set(ControlMode.PercentOutput, power);
    }
}
