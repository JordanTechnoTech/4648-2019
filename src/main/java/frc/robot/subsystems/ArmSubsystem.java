package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class ArmSubsystem implements TechnoTechSubsystem {
    public Counter counter;
    private WPI_TalonSRX shoulder, elbow;
    private Talon wrist;
    private Counter wristEncoder;

    public ArmSubsystem(int shoulder, int elbow, int wrist ,int wristEncoderChannel) {//TODO add args for all motors tied to the arm
        this.shoulder = new WPI_TalonSRX(shoulder);
        this.elbow = new WPI_TalonSRX(elbow);
        this.wrist = new Talon(wrist);
        this.shoulder.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        this.elbow.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        this.wristEncoder = new Counter(new AnalogTrigger(wristEncoderChannel));

    }

    @Override
    public void log() {
        SmartDashboard.putNumber("FrontLeft speed",this.shoulder.get());
        SmartDashboard.putNumber("FrontRight speed",this.elbow.get());
        SmartDashboard.putNumber("BackLeft speed",this.wrist.get());
        SmartDashboard.putNumber("shoulder sensor value", shoulder.getSelectedSensorPosition());
        SmartDashboard.putNumber("elbow sensor value", elbow.getSelectedSensorPosition());
        SmartDashboard.putNumber("wrist sensor value", counter.get());
    }

    public void moveWrist(double speed){
        //wristTalon.set(speed);
    }
    public void moveElbow(double speed){
       // elbowTalon.set(speed);
    }
    public void moveShoulder(double speed){
      //  shoulderTalon.set(speed);
    }
}
