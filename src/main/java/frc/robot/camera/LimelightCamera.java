package frc.robot.camera;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LimelightCamera {

  // camera mode enum
  public enum cameraMode {
    VISION(0), CAMERA(1);

    private final int value;

    cameraMode(int value) {
      this.value = value;
    }

    public int getValue() {
      return this.value;
    }
  }

  // LED mode enum
  public enum ledMode {
    DEFAULT(0), OFF(1), BLINK(2), ON(3);

    private final int value;

    ledMode(int value) {
      this.value = value;
    }

    public int getValue() {
      return this.value;
    }
  }


  //Table info
  public static double getTargetHorizontal() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
  }

  public static double getTargetVertical() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
  }

  public static double getTargetArea() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
  }

  public static double getTargetSkew() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ts").getDouble(0);
  }

  public static boolean hasTarget() {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0) == 1;
  }
  public static void log() {
	  SmartDashboard.putNumber("limelight.tv", NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0));
	  SmartDashboard.putNumber("limelight.tx", NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0));
	  SmartDashboard.putNumber("limelight.ty", NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0));
	  SmartDashboard.putNumber("limelight.ta", NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0));
  }


  /**
   * Sets the LED mode of the camera.
   *
   * @param mode the LED mode to set the camera to
   */
  public static void setLightMode(ledMode mode) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(mode.getValue());
  }

  /**
   * Sets the camera mode of the camera.
   *
   * @param mode the camera mode to set the camera to
   */
  public static void setCameraMode(cameraMode mode) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(mode.getValue());
  }

  /**
   * Sets the pipeline for the camera to use.
   *
   * @param pl the pipeline for the camera to use
   */
  public static void setPipeline(Number pl) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(pl);
  }


  /**
   * Return the distance from the camera to the target.
   *
   * @return the distance from the camera to the target
   */
  public static double getDistance(double targetHeight) {
    /*
     *Uses the equation: tan(a + ty) = (ht - hc) / d
     * a: the angle of the camera from the ground
     * ty: the measured angle of the target from the camera
     * ht: the height of the target
     * hc: the height of the camera
     * d: the distance
     */
    double a = 14.57;
    double ty = getTargetVertical();
    double hc = 22.0;
    return (targetHeight - hc) / Math.tan(Math.toRadians(a + ty));
  }

  /**
   * Assumes camera is 100cm away from target
   *
   * @param heightOfCamera
   * @param heightOfCrosshairs
   * @return
   */
  public static double figureOutCameraAngle(double heightOfCamera, double heightOfCrosshairs) {
    double actualHeight = heightOfCrosshairs - heightOfCamera;
    System.out.println("ACTUAL HEIGHT:" + actualHeight);
    double radians = Math.atan(actualHeight / 100);
    System.out.println("RADIANS:" + radians);
    double degrees = Math.toDegrees(radians);
    System.out.println("DEGREES:" + radians);
    return degrees;
  }
}
