package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TalonSrxMotorTestCommand extends Command {
    private WPI_TalonSRX motorController;

    public TalonSrxMotorTestCommand(int canChannelId) {
        SmartDashboard.putData("Talon SRX Test", this);
        motorController = new WPI_TalonSRX(canChannelId);
        motorController.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
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

    private void log() {
        SmartDashboard.putNumber("TalonSRXTestMotor raw", motorController.get());
        SmartDashboard.putNumber("TalonSRXTestMotor raw", motorController.getIntegralAccumulator());
        SmartDashboard.putNumber("TalonSRXTestMotor voltage", motorController.getBusVoltage());
        SmartDashboard.putNumber("TalonSRXTestMotor sensor value", motorController.getSelectedSensorPosition());
    }
}
