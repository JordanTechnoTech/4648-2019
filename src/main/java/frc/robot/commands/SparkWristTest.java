package frc.robot.commands;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SparkWristTest extends Command {
    private final AnalogInput analogInput;
    private final DigitalInput digitalInput;
    private Spark motorController;

    public SparkWristTest(int pwmChannel, AnalogInput analogInput, DigitalInput digitalInput) {
        SmartDashboard.putData("Talon Test",this);
        this.analogInput = analogInput;
        this.digitalInput = digitalInput;

        motorController = new Spark(pwmChannel);
    }

    @Override
    protected void execute() {
        XboxController controller1 = new XboxController(1);
        double leftY, rightY, leftX, twist;
        leftX = OI.deadZone(Robot.m_oi.getStickLeftXValue(), RobotMap.getTranslationaldeadzone());
        motorController.set(.5*leftX);
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
