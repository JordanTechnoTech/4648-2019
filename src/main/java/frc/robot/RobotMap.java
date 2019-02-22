/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.analog.adis16448.frc.ADIS16448_IMU;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.subsystems.*;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    //CAN BUS CHANNEL MAPPINGS
    private static final int canDriveFrontLeft = 1;
    private static final int canDriveFrontRight = 3;
    private static final int canDriveBackLeft = 2;
    private static final int canDriveBackRight = 0;

    private static final int canArmShoulderDeviceID = 4;
    private static final int canArmElbowDeviceID = 5;

    //DIO CHANNEL MAPPINGS
    private static final int dioSonarPingChannel = 4; //3wire
    private static final int dioSonarEchoChannel = 3;  //1wire to signal
    private static final int dioWristEncoderChannel = 2;

    //PWM CHANNEL MAPPINGS
    private static final int pwmWristChannel = 8;
    private static final int pwmVacuumChannel = 9;
    private static final int pwmPistonChannel = 0;


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

    public static MecanumDriveSubsystem drivetrain;
    public static ADIS16448_IMU imu;

    public static SonarSubsystem sonar;
    public static ShoulderSubsystem shoulderSubsystem;
    public static ElbowSubsystem elbowSubsystem;
    public static WristSubsystem wristSubsystem;
    public static ArmSubsystem armSubsystem;
    public static VacuumSubsystem vacuumSubsystem;
    public static PistonSubsystem pistonSubsystem;

    //   public static Counter wristCounterEncoder;
    public static Spark wristMotorController;

    public static double getRotationaldeadzone() {
        return rotationalDeadZone;
    }

    public static double getTranslationaldeadzone() {
        return translationalDeadZone;
    }

     public static void init() {
        imu = new ADIS16448_IMU();
        sonar = new SonarSubsystem(dioSonarPingChannel, dioSonarEchoChannel);
        drivetrain = new MecanumDriveSubsystem(canDriveFrontLeft, canDriveFrontRight, canDriveBackLeft, canDriveBackRight);
        armSubsystem = new ArmSubsystem(12, 11, pwmWristChannel, dioWristEncoderChannel);
        vacuumSubsystem = new VacuumSubsystem(pwmVacuumChannel);
        pistonSubsystem = new PistonSubsystem(pwmPistonChannel);
        elbowSubsystem = new ElbowSubsystem(canArmElbowDeviceID);
        shoulderSubsystem = new ShoulderSubsystem(canArmShoulderDeviceID);
    }
}