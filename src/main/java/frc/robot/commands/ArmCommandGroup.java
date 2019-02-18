package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ArmCommandGroup extends CommandGroup {
    private final double shoulderPosition;
    private final double elbowPosition;
    private final double wristPosition;

    public ArmCommandGroup(double shoulderPosition, double elbowPosition, double wristPosition) {
        super();
        this.shoulderPosition = shoulderPosition;
        this.elbowPosition = elbowPosition;
        this.wristPosition = wristPosition;
//        addSequential(new ResetArmCommand());
        addSequential(new ArmCommand(shoulderPosition, elbowPosition, wristPosition), 1);
    }


    @Override
    public synchronized void cancel() {
//        RobotMap.armSubsystem.stopShoulder();
//        RobotMap.armSubsystem.stopElbow();
        super.cancel();
    }
}
