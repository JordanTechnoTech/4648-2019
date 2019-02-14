package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.subsystems.ArmSubsystem;

public class ArmCommand extends Command {

    @Override
    protected void execute() {
        super.execute();
    }



    @Override
    public synchronized void cancel() {
        super.cancel();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
