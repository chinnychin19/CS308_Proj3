package test;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class SimpleTest {

    @Test
    public void testTurtleMovement () {
        Model myModel = new Model();
        double beforeX = myModel.getTurtleX();
        double beforeY = myModel.getTurtleY();
        double beforeAngle = myModel.getTurtleAngle();

        myModel.parseInput("fd 10");
        myModel.parseInput("fd -20");
        myModel.parseInput("right 90 fd 10");

        myModel.processNextInstruction();
        assertEquals("Foward positive", beforeY + 10, myModel.getTurtleY());
        myModel.processNextInstruction();
        assertEquals("Forward negative", beforeY - 10, myModel.getTurtleY());
        myModel.processNextInstruction();
        assertEquals("Rotate right", beforeAngle - 90, myModel.getTurtleAngle());
        myModel.processNextInstruction();
        assertEquals("Rotate right then forward", beforeX + 10, myModel.getTurtleX());
    }

    @Test
    public void testCommandHistory () {
        Model myModel = new Model();
        String[] testString = { "Command 1", "Command 2", "Command 3" };

        assertEquals("Empty command history", true, myModel.getHistory().isEmpty());

        for (int i = 0; i < testString.length; i++) {
            myModel.parseInput(testString[i]);
            assertEquals("String " + i, testString[i], myModel.getHistory().get(i));
        }
    }

    @Test
    public void testInstructionQueue () {
        Model myModel = new Model();
        assertEquals("Before anything added", false, myModel.hasNextInstruction());
        myModel.parseInput("fd 10");
        assertEquals("Add one instruction", true, myModel.hasNextInstruction());
        myModel.processNextInstruction();
        assertEquals("After process instruction", false, myModel.hasNextInstruction());
    }

}
