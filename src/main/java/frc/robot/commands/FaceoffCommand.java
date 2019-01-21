/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.camera.LimelightCamera;

/**
 * An example command.  You can replace me with your own command.
 */
public class FaceoffCommand extends Command {
  CameraTrackCommand.Target target;
  double initialZValue = 0.0;
  double initialSkew = 0.0;
  public FaceoffCommand(CameraTrackCommand.Target atarget) {
    target=atarget;
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_subsystem);
  }


  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    LimelightCamera.setLightMode(LimelightCamera.ledMode.ON);
    LimelightCamera.setPipeline(0);
    LimelightCamera.setCameraMode(LimelightCamera.cameraMode.VISION);
    initialZValue = RobotMap.imu.getAngleZ();
    initialSkew = LimelightCamera.getTargetSkew();
    if (LimelightCamera.getTargetSkew() <= -60) {
      initialSkew = initialSkew + 90;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      if (initialSkew > 2){
        RobotMap.leftDriveMotorController.set(.4);
        RobotMap.rightDriveMotorController.set(.4);
      } else if (initialSkew < -2){
        RobotMap.leftDriveMotorController.set(-.4);
        RobotMap.rightDriveMotorController.set(-.4);
      }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return initialSkew + initialZValue >= RobotMap.imu.getAngleZ() + 2;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
