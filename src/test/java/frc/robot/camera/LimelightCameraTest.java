package frc.robot.camera;

import org.junit.Test;

import static org.junit.Assert.*;

public class LimelightCameraTest {

    @Test
    public void findSkewDistance() {
        //Setup
        double distance = 100;
        double skew = 30;

        //When
        double actualDistance = LimelightCamera.findSkewDistance(distance,skew);

        //Then
        assertEquals(50.00,actualDistance,.01);
    }


}