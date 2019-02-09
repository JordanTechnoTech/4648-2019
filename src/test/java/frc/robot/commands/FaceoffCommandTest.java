package frc.robot.commands;

import frc.robot.camera.LimeLightValues;
import frc.robot.camera.LimelightCamera;
import org.junit.Test;

import static org.junit.Assert.*;

public class FaceoffCommandTest {

    @Test
    public void findTurn() {
        //Setup
        FaceoffCommand subject = new FaceoffCommand(FaceoffCommand.Target.PANEL_HOLE);
        LimeLightValues values = new LimeLightValues();
        values.tx = -11;

        //When
        double turnSpeed = subject.getTurnSpeed(values);

        //Then
        assertEquals(-0.3299,turnSpeed,.0001);


        subject = new FaceoffCommand(FaceoffCommand.Target.PANEL_HOLE);
        values = new LimeLightValues();
        values.tx = 11;

        //When
        turnSpeed = subject.getTurnSpeed(values);

        //Then
        assertEquals(0.3299,turnSpeed,.0001);
    }

    @Test
    public void slideSpeed() {
        //Setup
        FaceoffCommand subject = new FaceoffCommand(FaceoffCommand.Target.PANEL_HOLE);
        LimeLightValues values = new LimeLightValues();
        values.ts = -2;

        //When
        double slideSpeed = subject.getSlideSpeed(values, 4);

        //Then
        assertEquals(-.32, slideSpeed, .0001);
    }

    @Test
    public void testForwardSpeed(){
        //Setup
        FaceoffCommand subject = new FaceoffCommand(FaceoffCommand.Target.PANEL_HOLE);

        //When
        double forwardSpeed = subject.getForwardSpeed(50);

        //Then
        assertTrue(forwardSpeed>0);


        //When
        forwardSpeed = subject.getForwardSpeed(250);

        //Then
        assertTrue(forwardSpeed<0);


        //When
        forwardSpeed = subject.getForwardSpeed(115);

        //Then
        assertEquals(0.0,forwardSpeed,0.01);



    }
}