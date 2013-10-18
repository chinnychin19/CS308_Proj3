package test.french;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class TurtleTests {
    private static final double DELTA = 0.001;

    @Test
    public void testFD () {
        Model model = new Model();
        model.parseInput("fd 10");
        assertEquals(10, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testBK () {
        Model model = new Model();
        model.parseInput("bk 10");
        assertEquals(-10, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testLT () {
        Model model = new Model();
        model.parseInput("lt 10");
        assertEquals(100, model.getTurtleAngle(1), DELTA);
    }

    @Test
    public void testRT () {
        Model model = new Model();
        model.parseInput("rt 10");
        assertEquals(80, model.getTurtleAngle(1), DELTA);
    }

    @Test
    public void testSETH () {
        Model model = new Model();
        model.parseInput("seth 10");
        assertEquals(10, model.getTurtleAngle(1), DELTA);
    }

    @Test
    public void testTOWARDS () {
        Model model = new Model();
        model.parseInput("towards -10 10");
        assertEquals(135, model.getTurtleAngle(1), DELTA);
    }

    @Test
    public void testGOTO () {
        Model model = new Model();
        model.parseInput("goto -10 10");
        assertEquals(-10, model.getTurtleX(1), DELTA);
        assertEquals(10, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testPENUPDOWN () {
        Model model = new Model();
        model.parseInput("pd");
        assertEquals(true, model.isTurtleDrawing(1));
        model.parseInput("pu");
        assertEquals(false, model.isTurtleDrawing(1));
    }

    @Test
    public void testSHOWHIDE () {
        Model model = new Model();
        model.parseInput("st");
        assertEquals(true, model.isTurtleVisible(1));
        model.parseInput("ht");
        assertEquals(false, model.isTurtleVisible(1));
    }

    @Test
    public void testHOME () {
        Model model = new Model();
        model.parseInput("fd 100 lt 90 fd 100");
        // go 100, turn left, go 100
        assertEquals(-100, model.getTurtleX(1), DELTA);
        assertEquals(100, model.getTurtleY(1), DELTA);

        model.parseInput("home");
        assertEquals(0, model.getTurtleX(1), DELTA);
        assertEquals(0, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testCS () {
        Model model = new Model();
        model.parseInput("fd 100 lt 90 pu fd 50 pd fd 50");
        // go 100 w/ drawing, turn left, go 50 w/o drawing, then 50 w/ drawing
        assertEquals(2, model.getTurtlePaths().size());
        assertEquals(-100, model.getTurtleX(1), DELTA);
        assertEquals(100, model.getTurtleY(1), DELTA);

        model.parseInput("cs");
        assertEquals(0, model.getTurtlePaths().size());
        assertEquals(0, model.getTurtleX(1), DELTA);
        assertEquals(0, model.getTurtleY(1), DELTA);
    }
}
