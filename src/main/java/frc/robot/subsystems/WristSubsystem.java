package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.WristJoystickCommand;

public class WristSubsystem extends Subsystem implements TechnoTechSubsystem {
    private Spark wrist;
    private Counter wristEncoder;
    private double diff;

    public WristSubsystem(int pwmWristChannel, int wristEncoderChannel) {
        this.wrist = new Spark(pwmWristChannel);
        this.wristEncoder = new Counter(wristEncoderChannel);
    }

    @Override
    protected void initDefaultCommand() {
        wristEncoder.reset();
        setDefaultCommand(new WristJoystickCommand());
    }

    int storedWristPosition = 0;
    int lastPoll = 0;

    public void trackWristCounter() {
        if (wrist.getSpeed() == 0) {
            storedWristPosition = lastPoll;
        }
        if (wrist.getSpeed() > 0) {
            diff = wristEncoder.get() - lastPoll;
        } else {
            diff = lastPoll - wristEncoder.get();
        }
        storedWristPosition += diff;
    }

    public double getStoredWristPosition() {
        return storedWristPosition;
    }

    @Override
    public void log() {
        SmartDashboard.putNumber("Wrist motor position", getStoredWristPosition());
        SmartDashboard.putNumber("wrist motor Speed", wrist.getSpeed());
    }
//This is the part of the code that moves the Wrist
//The power is set in the WristJoystickCommand
    public void moveWristPower(double power) {
        this.wrist.set(power);

    }
//This is a function that just stops the wrist
    public void stopWrist() {
        this.wrist.stopMotor();
    }
}
