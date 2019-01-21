package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.camera.LimelightCamera;
import frc.robot.camera.LimelightCamera.ledMode;
import frc.robot.camera.LimelightCamera.cameraMode;

public class CameraTrackCommand extends Command {


  public CameraTrackCommand(Target target) {
    this.target = target;
  }

  private Target target;
  double defaultDriveForwardSpeed = .4D;

  public enum Target {
    ROCKET_BALL_HOLE(100.0d),
    PANEL_HOLE(75.0d);

    private final double height;

    Target(double v) {
      height = v;
    }

    public double getHeight() {
      return height;
    }
  }

  @Override
  protected void initialize() {
    LimelightCamera.setLightMode(ledMode.ON);
    LimelightCamera.setPipeline(0);
    LimelightCamera.setCameraMode(cameraMode.VISION);

    super.initialize();
  }

  @Override
  protected void execute() {
    //turn to target until in view
    SmartDashboard.putNumber("distance", LimelightCamera.getDistance(target.getHeight()));

    if (!LimelightCamera.hasTarget()) {
      RobotMap.leftDriveMotorController.set(-.3);
      RobotMap.rightDriveMotorController.set(-.3);
    } else {
      double kSetSpeed = getSpeed();

      float Kp = -0.06f;
      float min_command = 0.05f;
      float tx = (float) LimelightCamera.getTargetHorizontal();
      float angle = Math.abs(tx);
      if(angle <= 5){
        Kp = -.04f;
      } else if (angle <= 10){
        Kp = -.05f;
      } else if (angle <= 15){
        Kp = -.06f;
      } else if (angle <= 20){
        Kp = -.07f;
      }
      double skew = LimelightCamera.getTargetSkew();
      if(LimelightCamera.getTargetSkew() <= -60){
        skew = skew + 90;
      }
      double skewDistance = LimelightCamera.findSkewDistance(12.5,13.5);
      float heading_error = -tx;
      float steering_adjust = 0.0f;
      if (tx > 1.0) { //target is moving right
        steering_adjust = (Kp * heading_error);
      } else if (tx < 1.0) { //target is moving left
        steering_adjust = (Kp * heading_error);
      }
// from 0 to -27 degrees we are off to the right. need to slide to left
// from -90 to -70 you are off to the left. need to slide to right
      SmartDashboard.putNumber("SkewDistance", skewDistance);
      SmartDashboard.putNumber("limelightSkew", LimelightCamera.getTargetSkew());
      SmartDashboard.putNumber("limelightSteeringAdjust", steering_adjust);
      SmartDashboard.putNumber("skew", skew);


      RobotMap.leftDriveMotorController.set(kSetSpeed + steering_adjust);
      RobotMap.rightDriveMotorController.set(-kSetSpeed + steering_adjust);
    }
  }

  private double getSpeed() {
    double kSetSpeed = .4;
    if (LimelightCamera.getDistance(target.getHeight()) < 190.0D) {
      kSetSpeed = 0.0;
    }
    double distance = 0;
    if(LimelightCamera.getDistance(target.getHeight()) >= 100 && LimelightCamera.hasTarget()) {
      distance = LimelightCamera.getDistance(target.getHeight());
    } else{
      distance = (RobotMap.leftSonar.getValue() + RobotMap.rightSonar.getValue())/2;
    }

    if (distance <= 50) {
      kSetSpeed = 0d;
    } else if (distance <= 100){
      kSetSpeed = .2d;
    } else if (distance <= 150){
      kSetSpeed = .3d;
    } else if (distance <= 200) {
      kSetSpeed = .5d;
    } else if (distance <= 250) {
      kSetSpeed = .7d;
    }
    return kSetSpeed;
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
