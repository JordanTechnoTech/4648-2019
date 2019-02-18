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
  //  private double wristPosition;
    private boolean finished = false;

    public ArmCommand(double shoulderPosition, double elbowPosition, double wristPosition) {
        this.shoulderPosition = shoulderPosition;
        this.elbowPosition = elbowPosition;
    //    this.wristPosition = wristPosition;
        requires(RobotMap.armSubsystem);
    }

    @Override
    protected void execute() {
        RobotMap.armSubsystem.moveShoulder(shoulderPosition);
        RobotMap.armSubsystem.moveElbow(elbowPosition);
//        RobotMap.armSubsystem.moveWrist(wristPosition);
        finished = true;
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }
}
