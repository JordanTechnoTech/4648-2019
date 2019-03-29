package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PistonSubsystem extends Subsystem implements TechnoTechSubsystem {

    private final Spark motorController;
    Solenoid solenoid;

    public PistonSubsystem(int pwmChannel,int pistonCanChannel) {
        SmartDashboard.putData("PistonSubsystem", this);
        motorController = new Spark(pwmChannel);
        solenoid = new Solenoid(6,pistonCanChannel);
    }

    @Override
    protected void initDefaultCommand() {
    }

    public void togglePiston(boolean toggle) {
        solenoid.set(toggle);
    }
    public void setMidVacuumPower(double power){
        motorController.set(power);
        if (power > 0){
            SmartDashboard.putBoolean("SUCTION CENTER", true);
        } else{
            SmartDashboard.putBoolean("SUCTION CENTER", false);
        }
    }

    @Override
    public void log() {
        SmartDashboard.putBoolean("Solenoid value",solenoid.get());
    }
}
