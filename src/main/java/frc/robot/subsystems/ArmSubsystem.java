package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class ArmSubsystem implements TechnoTechSubsystem{
    public Counter counter;
    public ArmSubsystem(){
        counter = new Counter();
    }

    @Override
    public void log() {
        SmartDashboard.putBoolean("Wrist Encoder", RobotMap.wristCounterEncoder.get());
    }
    public static boolean getPosition() {
    return RobotMap.wristCounterEncoder.get();
    }

    }
