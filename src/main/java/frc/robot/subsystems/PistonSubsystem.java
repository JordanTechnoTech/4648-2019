package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PistonSubsystem extends Subsystem implements TechnoTechSubsystem {

    private final Spark motorController;

    public PistonSubsystem(int pwmChannel) {
        SmartDashboard.putData("PistonSubsystem", this);
        motorController = new Spark(pwmChannel);
    }

    @Override
    protected void initDefaultCommand() {
    }

    public void setPistonPower(double power) {
        motorController.set(power);
    }


    @Override
    public void log() {

    }
}
