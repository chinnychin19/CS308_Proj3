package test;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class BoolTests {

    private static final double DELTA = 0.001;

    @Test
    public void testLESSP () {
        Model model = new Model();
        model.parseInput("fd less? 1 2");
        assertEquals(1, model.getTurtleY(1), DELTA);
        model.parseInput("fd lessp 2 1");
        assertEquals(1, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testGREATERP () {
        Model model = new Model();
        model.parseInput("fd greater? 1 2");
        assertEquals(0, model.getTurtleY(1), DELTA);
        model.parseInput("fd greaterp 2 1");
        assertEquals(1, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testEQUALP () {
        Model model = new Model();
        model.parseInput("fd equal? 1 2");
        assertEquals(0, model.getTurtleY(1), DELTA);
        model.parseInput("fd equalp 2 2");
        assertEquals(1, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testNOTEQUALP () {
        Model model = new Model();
        model.parseInput("fd notequal? 1 2");
        assertEquals(1, model.getTurtleY(1), DELTA);
        model.parseInput("fd notequalp 2 2");
        assertEquals(1, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testAND () {
        Model model = new Model();
        model.parseInput("fd and 1 2");
        assertEquals(1, model.getTurtleY(1), DELTA);
        model.parseInput("fd and 1 0");
        assertEquals(1, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testOR () {
        Model model = new Model();
        model.parseInput("fd or 1 2");
        assertEquals(1, model.getTurtleY(1), DELTA);
        model.parseInput("fd or 1 0");
        assertEquals(2, model.getTurtleY(1), DELTA);
        model.parseInput("fd or 0 0");
        assertEquals(2, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testNOT () {
        Model model = new Model();
        model.parseInput("fd not 1");
        assertEquals(0, model.getTurtleY(1), DELTA);
        model.parseInput("fd not 0");
        assertEquals(1, model.getTurtleY(1), DELTA);
    }
}
