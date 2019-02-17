package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.talon.Gains;
import frc.robot.talon.TalonInitializer;

public class ArmSubsystem implements TechnoTechSubsystem {
    public Counter counter;
    private WPI_TalonSRX shoulder, elbow;
    private Talon wrist;
    private Counter wristEncoder;


    public ArmSubsystem(int shoulderCanId, int elbowCanId, int wrist ,int wristEncoderChannel) {//TODO add args for all motors tied to the arm
        this.shoulder = new WPI_TalonSRX(shoulderCanId);
        this.shoulder.setInverted(true);
        this.elbow = new WPI_TalonSRX(elbowCanId);
        Gains kGains = new Gains(0.2, 0, 1.2, 0.0, 0, 1.0);
        TalonInitializer.initTalon(this.shoulder, kGains);
        TalonInitializer.initTalon(this.elbow, kGains);

        this.wrist = new Talon(wrist);
        this.wristEncoder = new Counter(new AnalogTrigger(wristEncoderChannel));

    }

    @Override
    public void log() {
      //  SmartDashboard.putNumber("BackLeft speed",this.wrist.get());
      //  SmartDashboard.putNumber("wrist sensor value", counter.get());
        SmartDashboard.putNumber("elbow sensor value", elbow.getSelectedSensorPosition());
        SmartDashboard.putNumber("FrontRight speed",this.elbow.get());
        SmartDashboard.putNumber("Elbow sensor value", elbow.getSelectedSensorPosition());
        SmartDashboard.putNumber("FrontLeft speed",this.shoulder.get());
        SmartDashboard.putNumber("Shoulder sensor value", shoulder.getSelectedSensorPosition());
    }

    public void moveWrist(double speed){
        //wristTalon.set(speed);
    }
    public void moveElbow(double position){
        this.elbow.set(ControlMode.Position, position);

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
}
