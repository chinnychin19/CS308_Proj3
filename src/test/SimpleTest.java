package test;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class SimpleTest {
    private final double DELTA = 0.001;

    @Test
    public void testTurtleMovement () {
        Model.initModel();
        double beforeX = Model.getTurtleX();
        double beforeY = Model.getTurtleY();
        double beforeAngle = Model.getTurtleAngle();

        Model.parseInput("fd 10");
        Model.parseInput("fd -20");
        Model.parseInput("fd 20 fd -10");
        // myModel.parseInput("right 90 fd 10");
        
        Model.processNextInstruction();
        assertEquals("Foward positive", 10, Model.getTurtleY(), DELTA);

        Model.processNextInstruction();
        assertEquals("Forward negative", -10, Model.getTurtleY(), DELTA);
        
        Model.processNextInstruction();
        assertEquals("Foward positive", 10, Model.getTurtleY(), DELTA);

        Model.processNextInstruction();
        assertEquals("Forward negative", 0, Model.getTurtleY(), DELTA);
        
        // Model.processNextTurtleMove();
        // assertEquals("Rotate right", beforeAngle - 90, Model.getTurtleAngle(), DELTA);
        // Model.processNextTurtleMove();
        // assertEquals("Rotate right then forward", beforeX + 10, myModel.getTurtleX());
    }

    @Test
    public void testCommandHistory () {
        Model.initModel();
        String[] testString = { "Command 1", "Command 2", "Command 3" };

        assertEquals("Empty command history", true, Model.getHistory().isEmpty()); // initially
                                                                                   // empty

        for (int i = 0; i < testString.length; i++) {
            Model.parseInput(testString[i]);
            assertEquals("String " + i, testString[i], Model.getHistory().get(i));
        }
    }

    @Test
    public void testInstructionQueue () {
        Model.initModel();
        assertEquals("Before anything added", false, Model.hasNextInstruction());
        Model.parseInput("fd 10");
        assertEquals("Add one instruction", true, Model.hasNextInstruction());
        Model.processNextInstruction();
        assertEquals("After process instruction", false, Model.hasNextInstruction());
    }

}
