package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.talon.Gains;
// this command is for tuning the PID of the Talon SRXs on the Arm
public class ArmCommand extends Command {
    private double shoulderPosition;
    private double elbowPosition;
    private double wristPosition;
    private boolean finished = false;
    // the first number is the speed the arm will go to at 100% if unchecked
    // the sixth number is the top speed it will go to
    private Gains elbowGains = new Gains(1.85, 0, 1, 0.0, 0, .4, .2);
    private Gains shoulderGains = new Gains(1.85, 0, 1, 0.0, 0, .2, .3);

    public ArmCommand(double shoulderPosition, double elbowPosition, double wristPosition) {
        this.shoulderPosition = shoulderPosition;
        this.elbowPosition = elbowPosition;
        this.wristPosition = wristPosition;
        requires(RobotMap.shoulderSubsystem);
        requires(RobotMap.elbowSubsystem);
        requires(RobotMap.wristSubsystem);
    }

    int counter = 0;
    // This puts the PID values found above into the arm and shoulder subsystems
    @Override
    protected void execute() {
        RobotMap.runningAutoArm(true);
        counter++;
        SmartDashboard.putString("Arm mode", "AUTO:"+counter);
        RobotMap.elbowSubsystem.moveElbowToPosition(elbowPosition, elbowGains);
        RobotMap.shoulderSubsystem.moveShoulderToPosition(shoulderPosition, shoulderGains);
        finished = true;
    }
    // When it is called to cancel it will say it on the smart dashbord and set "runningAutoArm" on the RobotMap to false
    @Override
    public synchronized void cancel() {
        SmartDashboard.putString("Arm mode", "AUTO CANCELLED");
        RobotMap.runningAutoArm(false);
        super.cancel();
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }
}
