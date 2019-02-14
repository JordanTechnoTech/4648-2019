package frc.robot.commands;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TalonMotorTestCommand extends Command {
    private final AnalogInput analogInput;
    private final DigitalInput digitalInput;
    private Talon motorController;

    public TalonMotorTestCommand(int pwmChannel, AnalogInput analogInput, DigitalInput digitalInput) {
        SmartDashboard.putData("Talon Test",this);
        this.analogInput = analogInput;
        this.digitalInput = digitalInput;

        motorController = new Talon(pwmChannel);
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
        SmartDashboard.putNumber("TalonTestMotor raw",motorController.getRaw());
        SmartDashboard.putNumber("TalonTestMotor speed",motorController.getSpeed());
        if(analogInput != null){
            SmartDashboard.putNumber("TalonTestMotor analog ",analogInput.getValue());
        }
        if(digitalInput != null){
            SmartDashboard.putBoolean("TalonTestMotor digital ",digitalInput.get());
        }
    }
}
