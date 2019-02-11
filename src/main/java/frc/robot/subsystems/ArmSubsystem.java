package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class ArmSubsystem implements TechnoTechSubsystem{
    public Counter motorCounter;

    @Override
    public void log() {
        SmartDashboard.putNumber("Wrist Encoder", RobotMap.wristCounterEncoder.getValue());
    }
}
