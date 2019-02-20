/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
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
        controller0.bButton.toggleWhenPressed(new ResetArmCommand(0, 600, 0));

        controller1.yButton.toggleWhenPressed(new ResetArmCommand(500, 2000, 0));
        controller1.xButton.toggleWhenPressed(new ResetArmCommand(250, 2000, 0));
        controller1.aButton.toggleWhenPressed(new ResetArmCommand(0, 1600, 0));
        controller1.dpadUpButton.toggleWhenPressed(new ResetArmCommand(260,3000,40));
        controller1.dpadLeftButton.toggleWhenPressed(new ResetArmCommand(500,2000,20));
        controller1.dpadDownButton.toggleWhenPressed(new ResetArmCommand(0,1500,0));
        controller1.rbButton.toggleWhenPressed(new VacuumJoystickCommand());
        controller1.lbButton.toggleWhenPressed(new PistonJoystickCommand());

    }

    public static double deadZone(double val, double deadZone) {
        if (Math.abs(val) > deadZone) {
            if (val > 0) {
                return (val - deadZone) / (1 - deadZone);
            } else {
                return -(-val - deadZone) / (1 - deadZone);
            }
        }
        return 0;
    }
}
