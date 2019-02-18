package frc.robot.commands;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SparkWristTest extends Command {
    private final DigitalInput digitalInput;
    private final Counter counter;
    private Spark motorController;

    public SparkWristTest() {
        SmartDashboard.putData("Spark Wrist Test", this);
        this.digitalInput = new DigitalInput(5);
        counter = new Counter();
        counter.setSemiPeriodMode(true);
        counter.setUpSource(6);
        counter.setUpDownCounterMode();
        motorController = new Spark(5);
    }

    @Override
    protected void execute() {
        XboxController controller1 = new XboxController(1);
        double leftX;
        leftX = OI.deadZone(Robot.m_oi.getStickLeftXValue(), RobotMap.getTranslationaldeadzone());
        motorController.set(.5 * leftX);
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

    private void log() {
        SmartDashboard.putNumber("Spark Wrist Test raw", motorController.getRaw());
        SmartDashboard.putNumber("Spark Wrist Test speed", motorController.getSpeed());
        SmartDashboard.putBoolean("Spark Wrist Test digital ", digitalInput.get());
        SmartDashboard.putNumber("Spark Wrist Test counter raw", counter.get());
        SmartDashboard.putNumber("Spark Wrist Test counter distance", counter.getDistance());
      //  SmartDashboard.putNumber("Spark Wrist Test counter pid", counter.pidGet());
    }
}
