package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.MecanumDriveCommand;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class MecanumDriveSubsystem extends Subsystem implements TechnoTechSubsystem{

    protected MecanumDrive drive;

    public WPI_TalonSRX frontLeft, frontRight, backLeft, backRight;

    public MecanumDriveSubsystem(int frontLeft, int frontRight, int backLeft, int backRight){
        this.frontLeft = new WPI_TalonSRX(frontLeft);
        this.frontRight = new WPI_TalonSRX(frontRight);
        this.backLeft = new WPI_TalonSRX(backLeft);
        this.backRight = new WPI_TalonSRX(backRight);

        this.frontLeft.setNeutralMode(NeutralMode.Brake);
        this.frontLeft.setInverted(true);
        this.frontRight.setNeutralMode(NeutralMode.Brake);
        this.backLeft.setNeutralMode(NeutralMode.Brake);
        this.backRight.setNeutralMode(NeutralMode.Brake);


        drive = new MecanumDrive(this.frontLeft, this.backLeft, this.frontRight, this.backRight);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new MecanumDriveCommand());
    }


    public MecanumDrive getDrivetrain() {
        return drive;
    }

    @Override
    public void log() {
        SmartDashboard.putNumber("FrontLeft speed",this.frontLeft.get());
        SmartDashboard.putNumber("FrontRight speed",this.frontRight.get());
        SmartDashboard.putNumber("BackLeft speed",this.backLeft.get());
        SmartDashboard.putNumber("BackRight",this.backRight.get());

    }
}