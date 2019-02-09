package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.camera.LimelightCamera;
import frc.robot.subsystems.SonarSubsystem;

public class CloseoutCommand extends Command {


    public void initialize() {
        super.initialize();
    }

    public void execute() {
//        RobotMap.sonar.ultrasonic.ping();
        double distance = RobotMap.sonar.ultrasonic.getRangeInches() * 2.54;
        log();
        double forwardSpeed = getForwardSpeed(distance);
        SmartDashboard.putNumber("Closeout forwardSpeed",forwardSpeed);
        SmartDashboard.putNumber("Closeout distance",distance);
        if(forwardSpeed == 0.0){
            RobotMap.drivetrain.getDrivetrain().stopMotor();
        }else {
            RobotMap.drivetrain.getDrivetrain().driveCartesian(0, forwardSpeed, 0);
        }
    }

    public double getForwardSpeed(double distance) {
        double qSetSpeed;
        if (distance <= 10) {
            qSetSpeed = .25d;
        }else if (distance <= 20) {
            qSetSpeed = 0;
        } else if (distance <= 40) {
            qSetSpeed = -.05d;
        } else if (distance <= 60) {
            qSetSpeed = -.05d;
        } else if (distance <60) {
            qSetSpeed = -.05d;
        } else {
            qSetSpeed = -.3d;
        }
        return qSetSpeed;
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    public synchronized void cancel() {
        super.cancel();
    }

    public void log() {

    }
}
