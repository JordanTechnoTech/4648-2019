/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
        //turn to target until in view
        SmartDashboard.putNumber("distance", LimelightCamera.getDistance(target.getHeight()));

        if (!LimelightCamera.hasTarget()) {
            //TODO twist robot.
            //RobotMap.drivetrain.getDrivetrain().driveCartesian(0, 0, -0.4);
        } else {
            double kSetSpeed = getSpeed();

            float Kp = -0.06f;
            float min_command = 0.05f;
            float tx = (float) LimelightCamera.getTargetHorizontal();
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
            double skew = LimelightCamera.getTargetSkew();
            if (LimelightCamera.getTargetSkew() <= -60) {
                skew = skew + 90;
            }
            double skewDistance = LimelightCamera.findSkewDistance(12.5, 13.5);
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

            RobotMap.drivetrain.getDrivetrain().driveCartesian(-kSetSpeed, 0, -steering_adjust);

            // RobotMap.leftDriveMotorController.set(kSetSpeed + steering_adjust);
            //RobotMap.rightDriveMotorController.set(-kSetSpeed + steering_adjust);
        }
    }

    private double getSpeed() {
        double kSetSpeed;

        double skew = LimelightCamera.getTargetSkew();
        if(LimelightCamera.getTargetSkew() <= -60){
            skew = skew + 90;
        }

        if (skew <= -2) {
            kSetSpeed = -.4d;
        } else if (skew >= 2) {
            kSetSpeed = .4d;
        }  else {
            kSetSpeed = 0d;
        }
        return kSetSpeed;
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
}
