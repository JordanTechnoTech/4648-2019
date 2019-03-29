/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a
    //// joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    public final TechnoTechXBoxController controller0 = new TechnoTechXBoxController(0);
    public final TechnoTechXBoxController controller1 = new TechnoTechXBoxController(1);

    public OI() {
        controller0.lbButton.toggleWhenPressed(new AutoCommandGroup(FaceoffCommand.Target.PANEL_HOLE));
        controller0.rbButton.whileHeld(new CloseoutCommand());
        controller0.bButton.whenPressed(new ArmCommand(50, 1300, 0));

        //Panels high medium low
        controller1.yButton.whenPressed(new ArmCommand(1697, 5667, 0));
        controller1.xButton.whenPressed(new ArmCommand(37, 2577, 0));
        controller1.aButton.whenPressed(new ArmCommand(47, 450, 0));

        //Ball hole high medium low
        controller1.dpadUpButton.whenPressed(new ArmCommand(2051,7028,40));
        controller1.dpadLeftButton.whenPressed(new ArmCommand(0,3180,20));
        controller1.dpadDownButton.whenPressed(new ArmCommand(0,1172,0));

        controller1.rbButton.toggleWhenPressed(new VacuumJoystickCommand());
        controller1.lbButton.toggleWhenPressed(new PistonJoystickCommand());
        controller1.bButton.toggleWhenPressed(new ArmCommand(3576,1967,0));
        controller1.startButton.whenPressed(new Command() {
            @Override
            protected void execute() { RobotMap.runningAutoArm(false); }

            @Override
            protected boolean isFinished() { return false; }
        });
    }

    public static double deadZone(double val, double deadZone) {
        if (Math.abs(val) > deadZone) {
            if (val > 0) {
                return (val - deadZone) / (1 - deadZone);
            } else {
                return -(-val - deadZone) / (1 - deadZone);
            }
        }
        return 0.0;
    }
}
