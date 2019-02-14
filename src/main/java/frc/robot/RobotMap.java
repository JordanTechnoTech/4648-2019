/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.analog.adis16448.frc.ADIS16448_IMU;
import edu.wpi.first.wpilibj.*;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.MecanumDriveSubsystem;
import frc.robot.subsystems.SonarSubsystem;



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
    private static final double rotationalDeadZone = 0.15;
    private static final double translationalDeadZone = 0.15;

    public static Spark leftDriveMotorController;
    public static Spark rightDriveMotorController;
    public static MecanumDriveSubsystem drivetrain;
    public static ADIS16448_IMU imu;
    private static final int raiseFrontLeft  = 1;
    private static final int raiseFrontRight = 3;
    private static final int raiseBackLeft = 2;
    private static final int raiseBackRight = 4;
    public static SonarSubsystem sonar;
    public static ArmSubsystem armSubsystem;

    public static final int DIO_CHANNEL_WRIST_ENCODER = 2;
 //   public static Counter wristCounterEncoder;
    public static AnalogInput wristCounterEncoder;
    public static Talon wristMotorController;

    public static double getRotationaldeadzone() {
        return rotationalDeadZone;
    }
    public static double getTranslationaldeadzone() {
        return translationalDeadZone;
    }

    public static void init() {
//        wristMotorController = new Talon(0);
        wristCounterEncoder = new AnalogInput(2);
//        wristCounterEncoder = new AnalogInput(0);
        sonar = new SonarSubsystem();
        drivetrain = new MecanumDriveSubsystem(raiseFrontLeft, raiseFrontRight,raiseBackLeft, raiseBackRight);
		imu = new ADIS16448_IMU();
        armSubsystem = new ArmSubsystem(0,0,0,0);
    }
}