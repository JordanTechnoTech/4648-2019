package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.camera.LimelightCamera;
import frc.robot.camera.LimelightCamera.ledMode;
import frc.robot.camera.LimelightCamera.cameraMode;

public class CameraTrackCommand extends Command {
  double defaultDriveForwardSpeed = .4D;

  @Override
  protected void initialize() {
	LimelightCamera.setLightMode(ledMode.ON);
    LimelightCamera.setCameraMode(cameraMode.VISION);

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
      float angle = Math.abs(tx);
      if(angle <= 1){
        Kp = .25f;
      }
      else if (angle <= 2){
        Kp = .5f;
      }
      else if (angle <= 3){
        Kp = .75f;
      }
      else if (angle <= 4){
        Kp = .9f;
      }
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

  @Override
  public synchronized void cancel() {
    LimelightCamera.setLightMode(ledMode.OFF);
    LimelightCamera.setCameraMode(cameraMode.CAMERA);
    super.cancel();
  }
}
