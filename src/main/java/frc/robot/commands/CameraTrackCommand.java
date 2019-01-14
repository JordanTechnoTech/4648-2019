package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.camera.LimelightCamera;
import frc.robot.camera.LimelightCamera.ledMode;

public class CameraTrackCommand extends Command {
  double defaultDriveForwardSpeed = .4D;

  @Override
  protected void initialize() {
	LimelightCamera.setLightMode(ledMode.OFF);
    super.initialize();
  }

  @Override
  protected void execute() {
    //turn to target until in view
    if (!LimelightCamera.hasTarget()) {
      RobotMap.leftDriveMotorController.set(-.25);
      RobotMap.rightDriveMotorController.set(-.25);
    } else {
      double kSetSpeed = .3;
      if (LimelightCamera.getDistance() < 150.0D) {
        kSetSpeed = 0.0;
      }

      float Kp = -0.06f;
      float min_command = 0.05f;
      float tx = (float) LimelightCamera.getTargetHorizontal();
      float heading_error = -tx;
      float steering_adjust = 0.0f;
      if (tx > 1.0) { //target is moving right
        steering_adjust = (Kp * heading_error);
      } else if (tx < 1.0) { //target is moving left
        steering_adjust = (Kp * heading_error);
      }
      
      SmartDashboard.putNumber("limelightSteeringAdjust", steering_adjust);

      RobotMap.leftDriveMotorController.set(kSetSpeed + steering_adjust);
      RobotMap.rightDriveMotorController.set(-kSetSpeed + steering_adjust);
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
