package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SonarSubsystem extends Subsystem implements TechnoTechSubsystem {
    public Ultrasonic ultrasonic;

    public SonarSubsystem() {
        ultrasonic = new Ultrasonic(1,0);
        ultrasonic.setAutomaticMode(true);
    }

    public double ultrasonicRange() {
        return ultrasonic.getRangeInches();
    }

    public void ping(){
        ultrasonic.ping();
    }

    @Override
    protected void initDefaultCommand() {

    }

    @Override
    public void log() {
        SmartDashboard.putNumber("SonarValue",ultrasonicRange());
        SmartDashboard.putNumber("SonarValueCentimeters",ultrasonicRange()*2.54);
    }
}
