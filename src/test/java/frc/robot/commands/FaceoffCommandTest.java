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
    }
}