package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class WristCommand extends Command {
    private double wristPosition;
    private boolean finished = false;

    public WristCommand(double wristPosition){
        this.wristPosition = wristPosition;
        requires(RobotMap.wristSubsystem);
    }

    @Override
    protected void execute() {
        SmartDashboard.putString("Wrist mode","AUTO");
    }

    @Override
    protected boolean isFinished() { return finished;}
}
