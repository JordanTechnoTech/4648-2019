package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.ArmSubsystem;

public class ArmCommand extends Command {
    private double shoulderPosition;
    private double elbowPosition;
    private double wristPosition;

    public ArmCommand(double shoulderPosition, double elbowPosition, double wristPosition) {
        this.shoulderPosition = shoulderPosition;
        this.elbowPosition = elbowPosition;
        this.wristPosition = wristPosition;
        requires(RobotMap.armSubsystem);
    }

    @Override
    protected void initialize() {
        RobotMap.armSubsystem.moveShoulder(shoulderPosition);
        RobotMap.armSubsystem.moveElbow(elbowPosition);
        super.initialize();
    }
    @Override
    protected boolean isFinished() {
        return false;

    }
}
