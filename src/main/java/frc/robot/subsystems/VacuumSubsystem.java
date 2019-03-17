package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VacuumSubsystem extends Subsystem implements TechnoTechSubsystem {

    private final Spark motorController;
    Solenoid solenoid;

    public VacuumSubsystem(int pwmChannel, int releaseValveChannel) {
        SmartDashboard.putData("VacuumSubsystem", this);
        motorController = new Spark(pwmChannel);
        solenoid = new Solenoid(6,releaseValveChannel);
    }

    @Override
    protected void initDefaultCommand() {

    }
    public void toggleDumpValve(boolean toggle) {
        solenoid.set(toggle);
    }

    public void setVacuumPower(double power) {
        motorController.set(power);
        if (power > 0){
            SmartDashboard.putBoolean("SUCTION", true);
        } else{
            SmartDashboard.putBoolean("SUCTION", false);
        }

    }


    @Override
    public void log() {
        
    }
}
