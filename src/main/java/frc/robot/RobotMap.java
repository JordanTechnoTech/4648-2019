/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.frc.imu.ADIS16448_IMU;
import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.subsystems.MecanumDriveSubsystem;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;

    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
    private static final double rotationalDeadZone = 0.1;
    private static final double translationalDeadZone = 0.1;
    public static int rightDriveMotor = 0;
    public static int leftDriveMotor = 3;

    public static Spark leftDriveMotorController;
    public static Spark rightDriveMotorController;
    public static MecanumDriveSubsystem drivetrain;
    public static ADIS16448_IMU imu;
    public static AnalogInput leftSonar;
    public static AnalogInput rightSonar;
    private static final int raiseFrontLeft = 1;
    private static final int raiseFrontRight = 3;
    private static final int raiseBackLeft = 2;
    private static final int raiseBackRight = 0;
    public static double getRotationaldeadzone() {
        return rotationalDeadZone;
    }
    public static double getTranslationaldeadzone() {
        return translationalDeadZone;
    }

    public static void init() {
        // drive initialization
        leftDriveMotorController = new Spark(leftDriveMotor);
        rightDriveMotorController = new Spark(rightDriveMotor);
        drivetrain = new MecanumDriveSubsystem(raiseFrontLeft, raiseFrontRight,raiseBackLeft, raiseBackRight);
//		imu = new ADIS16448_IMU();
        leftSonar = new AnalogInput(0);
        rightSonar = new AnalogInput(1);
    }
}
