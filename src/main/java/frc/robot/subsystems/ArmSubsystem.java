package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class ArmSubsystem implements TechnoTechSubsystem{
    public Counter counter;
    public ArmSubsystem(){
        counter = new Counter();
    }

    @Override
    public void log() {
        SmartDashboard.putNumber("Wrist Encoder", RobotMap.wristCounterEncoder.getValue());
    }
    public static double getPosition() {
    return RobotMap.wristCounterEncoder.getValue();
    }

    }
