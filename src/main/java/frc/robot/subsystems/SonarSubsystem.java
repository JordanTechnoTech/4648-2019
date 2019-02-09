package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class SonarSubsystem extends Subsystem implements TechnoTechSubsystem {
    Ultrasonic ultrasonic = null;// new Ultrasonic(1,1);

    public SonarSubsystem() {
        //ultrasonic.setAutomaticMode(true);
    }

    public double ultrasonicRange() {
        return ultrasonic.getRangeInches();
    }

    @Override
    protected void initDefaultCommand() {

    }

    @Override
    public void log() {
        //SmartDashboard.putNumber("SonarValue",ultrasonicRange());
    }
}
