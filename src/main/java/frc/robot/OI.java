/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.commands.CameraTrackCommand;
import frc.robot.commands.FaceoffCommand;


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
  XboxController controller1 = new XboxController(1);

  Button stickLeft = new XBoxButton(controller1, XBoxButton.kStickLeft);
  Button stickRight = new XBoxButton(controller1, XBoxButton.kStickRight);
  Button bButton = new XBoxButton(controller1, XBoxButton.kB);
  Button rbButton = new XBoxButton(controller1, XBoxButton.kBumperRight);
  Button lbButton = new XBoxButton(controller1, XBoxButton.kBumperLeft);


  public OI() {
    rbButton.whileHeld(new CameraTrackCommand(CameraTrackCommand.Target.PANEL_HOLE));
    lbButton.whileHeld(new FaceoffCommand(CameraTrackCommand.Target.PANEL_HOLE));
  }

  //Forward back speed
  public double getArcardeSpeed() { // DriveCommand
    return controller1.getY(GenericHID.Hand.kLeft);
  }
  //Adjust for left and right turns
  public double getRotationSpeed() { // DriveCommand
    return controller1.getX(GenericHID.Hand.kLeft);
  }
  public double getStickRightYValue(){ return controller1.getY(GenericHID.Hand.kRight);}
  public double getStickRightXValue(){ return controller1.getX(GenericHID.Hand.kRight);}

  public double getStickLeftXValue() { // DriveCommand
    return controller1.getX(GenericHID.Hand.kLeft);
  }
  public static double deadZone (double val, double deadZone){
    if (Math.abs(val) > deadZone){
      if (val > 0){
        return (val - deadZone) / (1 - deadZone);
      } else {
        return -(-val - deadZone) / (1 - deadZone);
      }
    }
    return 0;
  }

}
