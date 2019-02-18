package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

public class TechnoTechXBoxController {
    final int channel;
    public final XBoxButton stickLeft;
    public final XBoxButton stickRight;
    public final XBoxButton rbButton;
    public final XBoxButton lbButton;
    public final XBoxButton yButton;
    public final XBoxButton xButton;
    public final XBoxButton bButton;
    public final XBoxButton aButton;
    XboxController controller; 

    public TechnoTechXBoxController(int channel) {
        this.channel = channel;
        this.controller = new XboxController(channel);
        this.stickLeft = new XBoxButton(controller, XBoxButton.kStickLeft);
        this.stickRight = new XBoxButton(controller, XBoxButton.kStickRight);
        this.rbButton = new XBoxButton(controller, XBoxButton.kBumperRight);
        this.lbButton = new XBoxButton(controller, XBoxButton.kBumperLeft);
        this.yButton = new XBoxButton(controller, XBoxButton.kY);
        this.aButton = new XBoxButton(controller, XBoxButton.kA);
        this.xButton = new XBoxButton(controller, XBoxButton.kX);
        this.bButton = new XBoxButton(controller, XBoxButton.kB);
    }

    public double getStickRightYValue(){ return controller.getY(GenericHID.Hand.kRight);}
    public double getStickLeftYValue(){ return controller.getY(GenericHID.Hand.kLeft);}
    public double getStickRightXValue(){ return controller.getX(GenericHID.Hand.kRight);}
    public boolean getAButtonValue(){
        return controller.getAButton();
    }
    public boolean getXButtonValue(){
        return controller.getXButton();
    }
    public boolean getYButtonValue(){
        return controller.getYButton();
    }

    public double getStickLeftXValue() {
        return controller.getX(GenericHID.Hand.kLeft);
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
