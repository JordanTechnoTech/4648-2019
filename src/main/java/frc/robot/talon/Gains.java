package frc.robot.talon;

public class Gains {
    public final double kP;
    public final double kI;
    public final double kD;
    public final double kF;
    public final int kIzone;
    public final double kPeakOutput;
    public final double kPeakOutputReverse;
    public boolean inverted = false;

    public Gains(double _kP, double _kI, double _kD, double _kF, int _kIzone, double _kPeakOutput, double _kPeakOutputReverse) {
        kP = _kP;
        kI = _kI;
        kD = _kD;
        kF = _kF;
        kIzone = _kIzone;
        kPeakOutput = _kPeakOutput;
        kPeakOutputReverse = _kPeakOutputReverse;
    }
}