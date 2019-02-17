package frc.robot.talon;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class TalonInitializer {
  public static void initTalon(WPI_TalonSRX _talon, Gains gains) {
    _talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
            Constants.kPIDLoopIdx,
            Constants.kTimeoutMs);

    /* Ensure sensor is positive when output is positive */
    _talon.setSensorPhase(Constants.kSensorPhase);

    /**
     * Set based on what direction you want forward/positive to be.
     * This does not affect sensor phase.
     */
    _talon.setInverted(Constants.kMotorInvert);

    /* Config the peak and nominal outputs, 12V means full */
    _talon.configNominalOutputForward(0, Constants.kTimeoutMs);
    _talon.configNominalOutputReverse(0, Constants.kTimeoutMs);
    _talon.configPeakOutputForward(1, Constants.kTimeoutMs);
    _talon.configPeakOutputReverse(-1, Constants.kTimeoutMs);

    /**
     * Config the allowable closed-loop error, Closed-Loop output will be
     * neutral within this range. See Table in Section 17.2.1 for native
     * units per rotation.
     */
    _talon.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

    /* Config Position Closed Loop gains in slot0, typically kF stays zero. */
    _talon.config_kF(Constants.kPIDLoopIdx, gains.kF, Constants.kTimeoutMs);
    _talon.config_kP(Constants.kPIDLoopIdx, gains.kP, Constants.kTimeoutMs);
    _talon.config_kI(Constants.kPIDLoopIdx, gains.kI, Constants.kTimeoutMs);
    _talon.config_kD(Constants.kPIDLoopIdx, gains.kD, Constants.kTimeoutMs);

    /**
     * Grab the 360 degree position of the MagEncoder's absolute
     * position, and initially set the relative sensor to match.
     */
    int absolutePosition = _talon.getSensorCollection().getPulseWidthPosition();

    /* Mask out overflows, keep bottom 12 bits */
    absolutePosition &= 0xFFF;
    if (Constants.kSensorPhase) {
      absolutePosition *= -1;
    }
    if (Constants.kMotorInvert) {
      absolutePosition *= -1;
    }

    /* Set the quadrature (relative) sensor to match absolute */
    _talon.setSelectedSensorPosition(absolutePosition, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
  }
}
