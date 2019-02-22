package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ArmJoystickCommand;
import frc.robot.talon.Gains;
import frc.robot.talon.TalonInitializer;

public class ArmSubsystem extends Subsystem implements TechnoTechSubsystem {
    private WPI_TalonSRX shoulder, elbow;
    private Spark wrist;

    private Counter wristEncoder;
    private double diff;

    public ArmSubsystem(int shoulderCanId, int elbowCanId, int pwmWristChannel, int wristEncoderChannel) {//TODO add args for all motors tied to the arm
        this.shoulder = new WPI_TalonSRX(shoulderCanId);
        this.shoulder.setInverted(true);
        this.elbow = new WPI_TalonSRX(elbowCanId);
        this.wrist = new Spark(pwmWristChannel);
        this.wristEncoder = new Counter(wristEncoderChannel);
    }

    public void initSubSystem() {
        Gains kGains1 = new Gains(0.23, 0, 1, 0.0, 0, 1.0);
        TalonInitializer.initTalon(this.shoulder, kGains1);
        Gains kGains2 = new Gains(0.35, 0, 1, 0.0, 0, 1.0);
        TalonInitializer.initTalon(this.elbow, kGains2);
        wristEncoder.reset();
        stopElbow();
        stopShoulder();
    }

    int storedWristPosition = 0;
    int lastPoll = 0;

    public void trackWristCounter() {
        if(wrist.getSpeed() == 0){
            return;
        }
        if (wrist.getSpeed() > 0) {
            diff =  wristEncoder.get() - lastPoll;
        } else {
            diff = -(wristEncoder.get() - lastPoll);
        }
        storedWristPosition += diff;
    }

    public double getStoredWristPosition() {
        return storedWristPosition;
    }

    @Override
    public void log() {
        SmartDashboard.putNumber("Elbow sensor value", elbow.getSelectedSensorPosition());
        SmartDashboard.putNumber("Shoulder sensor value", shoulder.getSelectedSensorPosition());
        SmartDashboard.putNumber("Shoulder motor output", shoulder.getMotorOutputPercent());
        SmartDashboard.putNumber("Elbow motor output", elbow.getMotorOutputPercent());
        SmartDashboard.putNumber("Wrist motor position", getStoredWristPosition());
        SmartDashboard.putNumber("wrist motor Speed", wrist.getSpeed());
    }

    //  public void moveWrist(double speed){this.wrist.set(speed);}

    public void moveElbow(double position) {
        this.elbow.set(ControlMode.Position, position);
    }

    public void moveElbowPower(double power) {
        this.elbow.set(ControlMode.PercentOutput, power);
    }

    public void moveShoulder(double position) {
        this.shoulder.set(ControlMode.Position, position);
    }

    public void stopElbow() {
        this.moveElbow(this.elbow.getSelectedSensorPosition()+600);
    }

    public void stopShoulder() {
        this.moveShoulder(this.shoulder.getSelectedSensorPosition()+600);
    }

    public int getElbowPosition() {
        return this.elbow.getSelectedSensorPosition();
    }

    public int getShoulderPosiion() {
        return this.shoulder.getSelectedSensorPosition();
    }

    @Override
    protected void initDefaultCommand() {
//        setDefaultCommand(new ArmJoystickCommand());
    }

    public void moveShoulderPower(double power) {
        this.shoulder.set(ControlMode.PercentOutput, power);
    }

    public void moveWristPower(double power) {
        this.wrist.set(power);

    }
}
