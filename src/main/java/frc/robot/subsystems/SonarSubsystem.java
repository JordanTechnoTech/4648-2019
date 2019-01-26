package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class SonarSubsystem extends Subsystem implements TechnoTechSubsystem {
    @Override
    protected void initDefaultCommand() {

    }

    @Override
    public void log() {
        SmartDashboard.putNumber("SonarLeftValue",RobotMap.leftSonar.getValue());
        SmartDashboard.putNumber("SonarLeftAverageValue",RobotMap.leftSonar.getAverageValue());
        SmartDashboard.putNumber("SonarLeftVolt",RobotMap.leftSonar.getVoltage());
        SmartDashboard.putNumber("SonarRightValue",RobotMap.rightSonar.getValue());
        SmartDashboard.putNumber("SonarRightAverageValue",RobotMap.rightSonar.getAverageValue());
        SmartDashboard.putNumber("SonarRightVolt",RobotMap.rightSonar.getVoltage());
    }
}
