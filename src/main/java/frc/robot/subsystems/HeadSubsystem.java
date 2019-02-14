package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SparkMotorTestCommand extends Command {
    private Spark motorController;

    public SparkMotorTestCommand(int pwmChannel) {
        SmartDashboard.putData("Spark Test",this);
        motorController = new Spark(pwmChannel);
    }

    @Override
    protected void execute() {
        motorController.set(.5);
        log();
        super.execute();
    }

    @Override
    public synchronized void cancel() {
        motorController.set(0);
        super.cancel();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    private void log(){
        SmartDashboard.putNumber("SparkTestMotor raw",motorController.getRaw());
        SmartDashboard.putNumber("SparkTestMotor speed",motorController.getSpeed());
    }
}


// This is a in progress Head Subsystem please correct if mistakes were made.
// This comment is about as useful as all other comments left by programmers in there code -- Michael Leonard Davis 2019
// "drive forward"-- Josh's First Command! Yay!
// "hack the mainframe" This is Joe's Fault.