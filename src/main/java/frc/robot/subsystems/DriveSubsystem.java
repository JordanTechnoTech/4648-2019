package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.frc.imu.ADIS16448_IMU;
import frc.robot.RobotMap;
import frc.robot.commands.DriveCommand;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class DriveSubsystem extends Subsystem implements TechnoTechSubsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private final Spark leftDrive = RobotMap.leftDriveMotorController;
  private final Spark rightDrive = RobotMap.rightDriveMotorController;
  private final DifferentialDrive differentialDrive1 = RobotMap.drivetrain;

  private final ADIS16448_IMU imu = RobotMap.imu;

  public DriveSubsystem() {
    addChild("Left CIM", leftDrive);
    addChild("Right CIM", rightDrive);
//    addChild("IMU", imu);
    differentialDrive1.setSafetyEnabled(false);
    differentialDrive1.setExpiration(0.1);
    differentialDrive1.setMaxOutput(1.0);
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveCommand());
  }

  public void arcadeDrive(double forwardSpeed, double rotationSpeed) {
    differentialDrive1.arcadeDrive(forwardSpeed, rotationSpeed);
  }

  public void log() {
//    SmartDashboard.putNumber("Gyro-Angle", imu.getAngle());
//    SmartDashboard.putNumber("Gyro-X", imu.getAngleX());
//    SmartDashboard.putNumber("Gyro-Y", imu.getAngleY());
//    SmartDashboard.putNumber("Gyro-Z", imu.getAngleZ());
//
//    SmartDashboard.putNumber("Pressure: ", imu.getBarometricPressure());
//    SmartDashboard.putNumber("Temperature: ", imu.getTemperature());

    SmartDashboard.putNumber("Left Speed", leftDrive.get());
    SmartDashboard.putNumber("Right Speed", rightDrive.get());

//    SmartDashboard.putNumber("Left Encoder", RobotMap.leftEncoder.get());
//    SmartDashboard.putNumber("Right Encoder", RobotMap.rightEncoder.get());
  }
}