package frc.robot.commands;

import org.junit.Test;

import static org.junit.Assert.*;

public class CloseoutCommandTest {

    @Test
    public void getForwardSpeed() {
        //setup
        double distance =10.0d;
        CloseoutCommand command = new CloseoutCommand();

        //when
        double forwardSpeed = command.getForwardSpeed(distance);

        //then
        assertEquals(0.25,forwardSpeed,0.1);

    }
}