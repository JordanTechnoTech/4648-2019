package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.camera.LimelightCamera;

public class CameraTrackCommand extends Command {
  double defaultDriveForwardSpeed = .4D;

  @Override
  protected void initialize() {
    super.initialize();
  }

  @Override
  protected void execute() {
    //turn to target until in view
    if (!LimelightCamera.hasTarget()) {
      RobotMap.leftDriveMotorController.set(-.4);
      RobotMap.rightDriveMotorController.set(-.4);
    } else {
      double kSetSpeed = .4;
      if (LimelightCamera.getDistance() < 4.0D) {
        kSetSpeed = 0;
      }

      float Kp = -0.1f;
      float min_command = 0.05f;
      float tx = (float) LimelightCamera.getTargetHorizontal();
      float heading_error = -tx;
      float steering_adjust = 0.0f;
      if (tx > 1.0) {
        steering_adjust = Kp * heading_error - min_command;
      } else if (tx < 1.0) {
        steering_adjust = Kp * heading_error + min_command;
      }

      RobotMap.leftDriveMotorController.set(kSetSpeed + steering_adjust);
      RobotMap.rightDriveMotorController.set(-kSetSpeed - steering_adjust);
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
