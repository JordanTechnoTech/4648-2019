package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */
//Will run the piston when it is plugged in
//For now it just runs the center suction cup
public class PistonJoystickCommand extends Command {
    private  boolean toggle = false;
    public PistonJoystickCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(RobotMap.pistonSubsystem);
        SmartDashboard.putData("PistonJoystickCommand", this);
        SmartDashboard.putBoolean("pistonInput",true);
    }

    // Called repeatedly when this Command is scheduled to run
    public void log() {
    }

    @Override
    protected void execute() {
    //    RobotMap.pistonSubsystem.togglePiston(false);
        RobotMap.pistonSubsystem.setMidVacuumPower(1);
    }

    @Override
    public synchronized void cancel() {
        toggle = !toggle;
        RobotMap.pistonSubsystem.setMidVacuumPower(0);
   //     RobotMap.pistonSubsystem.togglePiston(true);
        super.cancel();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }
}