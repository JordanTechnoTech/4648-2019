package frc.robot.commands;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SparkMotorTestCommand extends Command {
    private Spark motorController;

    public SparkMotorTestCommand(int pwmChannel) {
        SmartDashboard.putData("Spark Motor Test",this);
        motorController = new Spark(pwmChannel);
    }

    @Override
    protected void execute() {
        motorController.set(1);
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
