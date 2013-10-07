package test;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class TurtleTests {
    private final double DELTA = 0.001;

    @Test
    public void testCommandHistory () {
        Model.initModel();
        String[] testString = { "fd 10", "fd 10", "fd 10" };
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

    @Test
    public void testFD () {
        Model.initModel();
        Model.parseInput("fd 10");
        Model.processNextInstruction();
        assertEquals(10, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testBK () {
        Model.initModel();
        Model.parseInput("bk 10");
        Model.processNextInstruction();
        assertEquals(-10, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testLT () {
        Model.initModel();
        Model.parseInput("lt 10");
        Model.processNextInstruction();
        assertEquals(100, Model.getTurtle().getAngle(), DELTA);
    }

    @Test
    public void testRT () {
        Model.initModel();
        Model.parseInput("rt 10");
        Model.processNextInstruction();
        assertEquals(80, Model.getTurtle().getAngle(), DELTA);
    }

    @Test
    public void testSETH () {
        Model.initModel();
        Model.parseInput("seth 10");
        Model.processNextInstruction();
        assertEquals(10, Model.getTurtle().getAngle(), DELTA);
    }

    @Test
    public void testTOWARDS () {
        Model.initModel();
        Model.parseInput("towards -10 10");
        Model.processNextInstruction();
        assertEquals(135, Model.getTurtle().getAngle(), DELTA);
    }

    @Test
    public void testGOTO () {
        Model.initModel();
        Model.parseInput("goto -10 10");
        Model.processNextInstruction();
        assertEquals(-10, Model.getTurtle().getX(), DELTA);
        assertEquals(10, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testPENUPDOWN () {
        Model.initModel();

        Model.parseInput("pd");
        Model.processNextInstruction();
        assertEquals(true, Model.getTurtle().isDrawing());

        Model.parseInput("pu");
        Model.processNextInstruction();
        assertEquals(false, Model.getTurtle().isDrawing());
    }

    @Test
    public void testSHOWHIDE () {
        Model.initModel();

        Model.parseInput("st");
        Model.processNextInstruction();
        assertEquals(true, Model.getTurtle().isVisible());

        Model.parseInput("ht");
        Model.processNextInstruction();
        assertEquals(false, Model.getTurtle().isVisible());
    }

    @Test
    public void testHOME () {
        Model.initModel();
        Model.parseInput("fd 100 lt 90 fd 100");
        // go 100, turn left, go 100
        while (Model.hasNextInstruction()) {
            Model.processNextInstruction();
        }
        assertEquals(-100, Model.getTurtle().getX(), DELTA);
        assertEquals(100, Model.getTurtle().getY(), DELTA);

        Model.parseInput("home");
        Model.processNextInstruction();
        assertEquals(0, Model.getTurtle().getX(), DELTA);
        assertEquals(0, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testCS () {
        Model.initModel();
        Model.parseInput("fd 100 lt 90 pu fd 50 pd fd 50");
        // go 100 w/ drawing, turn left, go 50 w/o drawing, then 50 w/ drawing
        while (Model.hasNextInstruction()) {
            Model.processNextInstruction();
        }
        assertEquals(2, Model.getTurtlePaths().size());
        assertEquals(-100, Model.getTurtle().getX(), DELTA);
        assertEquals(100, Model.getTurtle().getY(), DELTA);

        Model.parseInput("cs");
        Model.processNextInstruction();
        assertEquals(0, Model.getTurtlePaths().size());
        assertEquals(0, Model.getTurtle().getX(), DELTA);
        assertEquals(0, Model.getTurtle().getY(), DELTA);
    }
}
