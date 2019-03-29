/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.camera.LimelightCamera;
import frc.robot.camera.LimelightCamera.ledMode;
import frc.robot.commands.*;
import frc.robot.subsystems.TechnoTechSubsystem;

import java.util.ArrayList;
import java.util.List;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    public static OI m_oi;

    Command m_autonomousCommand;
    SendableChooser<Command> m_chooser = new SendableChooser<>();

    List<TechnoTechSubsystem> subsystems = new ArrayList<>();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        RobotMap.init();
        m_oi = new OI();
//    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
        // chooser.addOption("My Auto", new MyAutoCommand());
//    SmartDashboard.putData("Auto mode", m_chooser);
//        new TalonSrxMotorTestCommand(5);
//        new SparkWristTest(0, new AnalogInput(0), null);
//        new SparkMotorTestCommand(5);
        initSubsystems();
        new VacuumJoystickCommand();
        new PistonJoystickCommand();
//        new ElbowCommand();
//        new ShoulderCommand();
        new WristCommand(0);
    }

    public void initSubsystems() {
        subsystems.add(RobotMap.drivetrain);
        subsystems.add(RobotMap.sonar);
        subsystems.add(RobotMap.elbowSubsystem);
        subsystems.add(RobotMap.shoulderSubsystem);
        subsystems.add(RobotMap.wristSubsystem);
        subsystems.add(RobotMap.vacuumSubsystem);
        subsystems.add(RobotMap.pistonSubsystem);
    }

    /**
     * This function is called every robot packet, no matter the mode. Use
     * this for items like diagnostics that you want ran during disabled,
     * autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     *
     * <p>You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {
        m_autonomousCommand = m_chooser.getSelected();

        /*
         * String autoSelected = SmartDashboard.getString("Auto Selector",
         * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
         * = new MyAutoCommand(); break; case "Default Auto": default:
         * autonomousCommand = new ExampleCommand(); break; }
         */

        // schedule the autonomous command (example)
//        if (m_autonomousCommand != null) {
//            m_autonomousCommand.start();
//        }
        RobotMap.elbowSubsystem.resetTalonEncoder();
        RobotMap.shoulderSubsystem.resetTalonEncoder();
        LimelightCamera.setLightMode(ledMode.OFF);
        LimelightCamera.setCameraMode(LimelightCamera.cameraMode.CAMERA);
        LimelightCamera.setPipeline(1);
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
//        RobotMap.armSubsystem.initSubSystem();


        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }

        CameraServer.getInstance().startAutomaticCapture();
        // starting position goes here
        //
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        log();
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }

    public void log() {
        SmartDashboard.putNumber("SONAR" ,RobotMap.sonar.getRangeCentimeters());
        SmartDashboard.putNumber("Gyro-Angle", RobotMap.imu.getAngle());
        SmartDashboard.putNumber("Gyro-X", RobotMap.imu.getAngleX());
        SmartDashboard.putNumber("Gyro-Y", RobotMap.imu.getAngleY());
        SmartDashboard.putNumber("Gyro-Z", RobotMap.imu.getAngleZ());
        SmartDashboard.putNumber("Accel-X", RobotMap.imu.getAccelX());
        SmartDashboard.putNumber("Accel-Y", RobotMap.imu.getAccelY());
        SmartDashboard.putNumber("Accel-Z", RobotMap.imu.getAccelZ());
//        SmartDashboard.putNumber("Wrist Encoder Analog:", RobotMap.wristCounterEncoder.getValue());
        LimelightCamera.log();
        subsystems.forEach(TechnoTechSubsystem::log);
    }
}
