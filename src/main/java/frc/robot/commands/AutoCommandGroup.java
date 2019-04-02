package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;
// this starts off the limelight camera command then when it finishes
// it starts the sonar command after half a second
public class AutoCommandGroup extends CommandGroup {
    public AutoCommandGroup(FaceoffCommand.Target target) {
        addSequential(new FaceoffCommand(target));
        addSequential(new TimedCommand(.5));
        addSequential(new CloseoutCommand());
    }


}
