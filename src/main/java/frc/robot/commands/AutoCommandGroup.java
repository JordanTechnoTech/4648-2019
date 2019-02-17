package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class AutoCommandGroup extends CommandGroup {
    public AutoCommandGroup(FaceoffCommand.Target target) {
        addSequential(new FaceoffCommand(target));
        addSequential(new TimedCommand(.5));
        addSequential(new CloseoutCommand());
    }


}
