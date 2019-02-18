package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VacuumSubsystem extends Subsystem {

    private final Spark motorController;

    public VacuumSubsystem(int pwmChannel) {
        SmartDashboard.putData("VacuumSubsystem", this);
        motorController = new Spark(pwmChannel);
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void setVacuumPower(double power) {
        motorController.set(power);
    }


}
