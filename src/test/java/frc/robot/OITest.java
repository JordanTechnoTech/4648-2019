package frc.robot;

import org.junit.Test;

import static org.junit.Assert.*;

public class OITest {

    @Test
    public void testDeadZone() {
        assertEquals(0.0,OI.deadZone(.15,.25),.0);
        assertEquals(0.02,OI.deadZone(.27,.25),.01);
        assertEquals(0.04,OI.deadZone(.28,.25),.01);
        assertEquals(0.33,OI.deadZone(.5,.25),.01);
        assertEquals(0.66,OI.deadZone(.75,.25),.01);
        assertEquals(0.98,OI.deadZone(.99,.25),.01);
        assertEquals(0.0,OI.deadZone(-.15,.25),.0);
        assertEquals(-0.02,OI.deadZone(-.27,.25),.01);
        assertEquals(-0.04,OI.deadZone(-.28,.25),.01);
        assertEquals(-0.33,OI.deadZone(-.5,.25),.01);
        assertEquals(-0.66,OI.deadZone(-.75,.25),.01);
        assertEquals(-0.98,OI.deadZone(-.99,.25),.01);
    }
}