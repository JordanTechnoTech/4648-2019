/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.camera.LimeLightValues;
import frc.robot.camera.LimelightCamera;

/**
 * An example command.  You can replace me with your own command.
 */
public class FaceoffCommand extends Command {
    FaceoffCommand.Target target;
    LimelightCamera limelightCamera = new LimelightCamera();
    double initialZValue = 0.0;
    double initialSkew = 0.0;
    double cameraFail;

    public FaceoffCommand(FaceoffCommand.Target atarget) {
        target = atarget;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        LimelightCamera.setLightMode(LimelightCamera.ledMode.ON);
        LimelightCamera.setPipeline(0);
        LimelightCamera.setCameraMode(LimelightCamera.cameraMode.VISION);

        super.initialize();
    }

    // Called repeatedly when this Command is scheduled to run

    @Override
    protected void execute() {
        LimeLightValues limeLightValues = limelightCamera.poll();
        //turn to target until in view
        double distance = LimelightCamera.getDistance(target.getHeight(),limeLightValues.getTargetVertical());
        SmartDashboard.putNumber("distance", distance);
        if (!limeLightValues.hasTarget()) {
            cameraFail = cameraFail + 1;
            SmartDashboard.putNumber("CameraFail", cameraFail);
        } else {
            double slideSpeed = getSlideSpeed(limeLightValues);
            double forwardSpeed = getForwardSpeed(limeLightValues);

            float Kp = -0.06f;
            float tx = (float) limeLightValues.getTargetHorizontal();
            float angle = Math.abs(tx);
            if (angle <= 5) {
                Kp = -.02f;
            } else if (angle <= 10) {
                Kp = -.025f;
            } else if (angle <= 15) {
                Kp = -.03f;
            } else if (angle <= 20) {
                Kp = -.035f;
            }
            double skew = limeLightValues.getTargetSkew();
            if (limeLightValues.getTargetSkew() <= -60) {
                skew = skew + 90;
            }
            //distance and skew need to be input to this thing I don't have any idea why this has
            //been working we don't know how much the command even needs to go
            double skewDistance = LimelightCamera.findSkewDistance(distance, skew);
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
            SmartDashboard.putNumber("limelightSkew", limeLightValues.getTargetSkew());
            SmartDashboard.putNumber("limelightSteeringAdjust", steering_adjust);
            SmartDashboard.putNumber("skew", skew);

            RobotMap.drivetrain.getDrivetrain().driveCartesian(-slideSpeed, forwardSpeed, -steering_adjust);

            // RobotMap.leftDriveMotorController.set(kSetSpeed + steering_adjust);
            //RobotMap.rightDriveMotorController.set(-kSetSpeed + steering_adjust);
        }
    }
    private double getSlideSpeed(LimeLightValues limeLightValues) {
        double kSetSpeed;
        double skew = limeLightValues.getTargetSkew();
        double distance = LimelightCamera.getDistance(target.getHeight(),limeLightValues.getTargetVertical());
        double skewDistance = LimelightCamera.findSkewDistance(distance, skew);
        if(limeLightValues.getTargetSkew() <= -60){
            skew = skew + 90;
        }

        if (Math.abs(skewDistance) <= 3){
            kSetSpeed = 0d;
        } else if (skew <= -3) {
            kSetSpeed = -.3d;
        } else if (skew >= 3) {
            kSetSpeed = .3d;
        }  else {
            kSetSpeed = 0d;
        }
        return kSetSpeed;
    }

    private double getForwardSpeed(LimeLightValues limeLightValues) {
        double vSetSpeed = 0;
        double distance = LimelightCamera.getDistance(target.getHeight(),limeLightValues.getTargetVertical());

        if (distance <= 50) {
            vSetSpeed = 0d;
        } else if(distance <= 150){
            vSetSpeed = -.2d;
        } else if (distance <= 200){
            vSetSpeed = -.25d;
        } else if (distance <=   250){
            vSetSpeed = -.35d;
        } else if (distance <= 300) {
            vSetSpeed = -.45d;
        } else if (distance <= 350) {
            vSetSpeed = -.55d;
        }
        return vSetSpeed;
    }



    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    public synchronized void cancel() {
        LimelightCamera.setLightMode(LimelightCamera.ledMode.OFF);
        LimelightCamera.setCameraMode(LimelightCamera.cameraMode.CAMERA);
        super.cancel();
    }

    public enum Target {
        ROCKET_BALL_HOLE(100.0d),
        PANEL_HOLE(71.0d);

        private final double height;

        Target(double v) {
            height = v;
        }

        public double getHeight() {
            return height;
        }
    }
}
