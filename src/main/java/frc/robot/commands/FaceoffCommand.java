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

import java.util.Arrays;
import java.util.List;

/**
 * An example command.  You can replace me with your own command.
 */
public class FaceoffCommand extends Command {
    public static final double degreesAdjust = 2;  //OFFSET FOR CAMERA ANGLE ADJUST increase number to angle more to right

    public static List<RangeValue> forwardSpeedRangeValues = Arrays.asList(
            new RangeValue(-999, 110, .25),
            new RangeValue(110, 150, 0),
            new RangeValue(150, 200, -.2),
            new RangeValue(200, 350, -.3),
            new RangeValue(350, 9999, -.4)
    );

    public static List<RangeValue> slideSpeedRangeValues = Arrays.asList(
            new RangeValue(-999, -4, -.3),
            new RangeValue(-4, -2, -.28),
            new RangeValue(-2, 2, 0),
            new RangeValue(2, 4, .28),
            new RangeValue(4, 9999, .3)
    );

    public static List<RangeValue> turnSpeedRangeValues = Arrays.asList(
            new RangeValue(-999, 1, 0.0),
            new RangeValue(1, 5, -.023),
            new RangeValue(5, 10, -.025),
            new RangeValue(10, 15, -.03),
            new RangeValue(15, 9999, -.035)
    );


    FaceoffCommand.Target target;
    LimelightCamera limelightCamera = new LimelightCamera();
    double cameraFail;
    LimeLightValues limeLightValues;
    private boolean finished = false;

    public FaceoffCommand(FaceoffCommand.Target atarget) {
        target = atarget;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        finished = false;
        LimelightCamera.setLightMode(LimelightCamera.ledMode.ON);
        LimelightCamera.setPipeline(0);
        LimelightCamera.setCameraMode(LimelightCamera.cameraMode.VISION);

        super.initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        limeLightValues = limelightCamera.poll();
        //turn to target until in view
        double distance = LimelightCamera.getDistance(target.getHeight(), limeLightValues.getTargetVertical());
        SmartDashboard.putNumber("distance", distance);
        SmartDashboard.putNumber("cachedTA", limeLightValues.ta);
        SmartDashboard.putNumber("cachedTX", limeLightValues.tx);
        SmartDashboard.putNumber("cachedTY", limeLightValues.ty);
        SmartDashboard.putNumber("cachedTS", limeLightValues.ts);
        if (!limeLightValues.hasTarget()) {
            cameraFail = cameraFail + 1;
            SmartDashboard.putNumber("CameraFail", cameraFail);
        } else {
            double slideSpeed = 0.0;
            if (distance < 200)
                slideSpeed = getSlideSpeed(limeLightValues, distance);
            double forwardSpeed = getForwardSpeed(distance < 150 ? RobotMap.sonar.getRangeCentimeters() + 40 : distance);  //for this only pass in distance from sonar if distance is under 150
            if (slideSpeed == 0 && forwardSpeed == 0 && limeLightValues.tx > -1 && limeLightValues.tx <1) {
                finished = true;
                RobotMap.drivetrain.getDrivetrain().stopMotor();
                return;
            }
            double turnSpeed = getTurnSpeed(limeLightValues);

            SmartDashboard.putNumber("limelightSkew", limeLightValues.getTargetSkew());
            SmartDashboard.putNumber("faceOffTTurnSpeed", turnSpeed);
            SmartDashboard.putNumber("faceOffForwardSpeed", forwardSpeed);
            SmartDashboard.putNumber("faceOffTSlideSpeed", slideSpeed);

            RobotMap.drivetrain.getDrivetrain().driveCartesian(-slideSpeed, forwardSpeed, -turnSpeed);
        }
    }

    public double findInCollection(List<RangeValue> rangeValues, double itemToFind) {
        for (RangeValue rangeValue : rangeValues) {
            if (itemToFind > rangeValue.startValue && itemToFind <= rangeValue.endValue) {
                return rangeValue.result;
            }
        }
        throw new RuntimeException("Item not found:" + itemToFind);
    }

    public double getTurnSpeed(LimeLightValues limeLightValues) {
        float tx = (float) limeLightValues.getTargetHorizontal();
        float angle = Math.abs(tx);

        float Kp = (float) findInCollection(turnSpeedRangeValues, angle);

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
        double skew = limeLightValues.getTargetSkew();
        if (skew <= -60) {
            skew = skew + 90;
        }
        final double skewDistance = LimelightCamera.findSkewDistance(distance, skew) + degreesAdjust;
        SmartDashboard.putNumber("SkewDistance", skewDistance);
        return findInCollection(slideSpeedRangeValues, skewDistance);
    }

    public double getForwardSpeed(double distance) {
        return findInCollection(forwardSpeedRangeValues, distance);
    }

    @Override
    protected boolean isFinished() {
        if(finished){
            LimelightCamera.setLightMode(LimelightCamera.ledMode.OFF);
            LimelightCamera.setPipeline(1);
            LimelightCamera.setCameraMode(LimelightCamera.cameraMode.CAMERA);
        }
        return finished;
    }

    @Override
    public synchronized void cancel() {
        LimelightCamera.setLightMode(LimelightCamera.ledMode.OFF);
        LimelightCamera.setCameraMode(LimelightCamera.cameraMode.CAMERA);
        LimelightCamera.setPipeline(1);
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
