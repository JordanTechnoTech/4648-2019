package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//This sets up the Sonar... obviously
public class SonarSubsystem extends Subsystem implements TechnoTechSubsystem {
    public Ultrasonic ultrasonic;

    public SonarSubsystem(int dioSonarPingChannel, int dioSonarEchoChannel) {
        SmartDashboard.putData("SonarSubsystem",this);
        ultrasonic = new Ultrasonic(dioSonarPingChannel, dioSonarEchoChannel);
        ultrasonic.setAutomaticMode(true);
    }
//This returns the range as well as converting it from Inches to the superior Centimeters
    public double getRangeCentimeters() {
        return ultrasonic.getRangeInches() * 2.54;
    }

    @Override
    protected void initDefaultCommand() {
    }

    @Override
    public void log() {
        SmartDashboard.putNumber("SonarValueInCentimeters", getRangeCentimeters());
    }
}
