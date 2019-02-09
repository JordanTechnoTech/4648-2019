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
    public static final double degreesAdjust = -1.5;  //OFFSET FOR CAMERA ANGLE ADJUST

    FaceoffCommand.Target target;
    LimelightCamera limelightCamera = new LimelightCamera();
    double initialZValue = 0.0;
    double initialSkew = 0.0;
    double cameraFail;

    public FaceoffCommand(FaceoffCommand. Target atarget) {
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
        SmartDashboard.putNumber("cachedTA", limeLightValues.ta);
        SmartDashboard.putNumber("cachedTX", limeLightValues.tx);
        SmartDashboard.putNumber("cachedTY", limeLightValues.ty);
        SmartDashboard.putNumber("cachedTS", limeLightValues.ts);
        if (!limeLightValues.hasTarget()) {
            cameraFail = cameraFail + 1;
            SmartDashboard.putNumber("CameraFail", cameraFail);
        } else {
            double slideSpeed = getSlideSpeed(limeLightValues,distance);
            double forwardSpeed = getForwardSpeed(distance);  //for this only pass in distance from sonar if distance is under 150
            double turnSpeed = getTurnSpeed(limeLightValues);


// from 0 to -27 degrees we are off to the right. need to slide to left
// from -90 to -70 you are off to the left. need to slide to right
            SmartDashboard.putNumber("limelightSkew", limeLightValues.getTargetSkew());
            SmartDashboard.putNumber("faceOffTTurnSpeed", turnSpeed);
            SmartDashboard.putNumber("faceOffForwardSpeed", forwardSpeed);
            SmartDashboard.putNumber("faceOffTSlideSpeed", slideSpeed);


            RobotMap.drivetrain.getDrivetrain().driveCartesian(-slideSpeed, forwardSpeed, -turnSpeed);
//            RobotMap.drivetrain.getDrivetrain().driveCartesian(-slideSpeed, 0, -turnSpeed);
//            RobotMap.drivetrain.getDrivetrain().driveCartesian(-slideSpeed, 0, -turnSpeed);

            // RobotMap.leftDriveMotorController.set(kSetSpeed + steering_adjust);
            //RobotMap.rightDriveMotorController.set(-kSetSpeed + steering_adjust);
        }
    }

    public double getTurnSpeed(LimeLightValues limeLightValues) {
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

        float heading_error = -tx;
        float steering_adjust = 0.0f;
        if (tx > 1.0) { //target is moving right
            steering_adjust = (Kp * heading_error);
        } else if (tx < 1.0) { //target is moving left
            steering_adjust = (Kp * heading_error);
        }

        return steering_adjust;
    }

    public double getSlideSpeed(LimeLightValues limeLightValues, double distance) {
        double kSetSpeed;
        double skew = limeLightValues.getTargetSkew();
        if(skew <= -60){
            skew = skew + 90;
        }
        double skewDistance = LimelightCamera.findSkewDistance(distance, skew);
        skewDistance = skewDistance + degreesAdjust;
        if (skewDistance <= -.5) {
            kSetSpeed = -.32d;
        } else if (skewDistance >= .5) {
            kSetSpeed = .32d;
        } else if (skewDistance <= -2) {
            kSetSpeed = -.4d;
        } else if (skewDistance >= 2) {
            kSetSpeed = .4d;
        } else if (skewDistance <= -3) {
            kSetSpeed = -.45d;
        } else if (skewDistance >= 3) {
            kSetSpeed = .45d;
        } else if (skewDistance <= -4) {
            kSetSpeed = -.5d;
        } else if (skewDistance >= 4) {
            kSetSpeed = .5d;
        }else {
            kSetSpeed = 0;
        }
        return kSetSpeed;
    }

    public double getForwardSpeed(double distance) {
        double vSetSpeed;
        //TODO if your under 100 cm in then reverse slowly only set speed to 0 when
        // range between 110 and 120
        if (distance <= 100){
            RobotMap.sonar.ultrasonicRange();
            vSetSpeed = .15;
        } else if (distance <= 120){
            vSetSpeed = .0d;
           // new CloseoutCommand();
           // return 0.0;
        } else if(distance <= 150){
            vSetSpeed = -.2d;
        } else if (distance <= 200){
            vSetSpeed = -.25d;
        } else if (distance <=   250){
            vSetSpeed = -.3d;
        } else if (distance <= 300) {
            vSetSpeed = -.35d;
        } else if (distance <= 350) {
            vSetSpeed = -.4d;
        } else {
            vSetSpeed = -.45d;
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
