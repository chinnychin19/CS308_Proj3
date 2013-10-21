package test;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class MultiturtleTests {
    private static final double DELTA = 0.001;

    @Test
    public void testID () {
        Model model = new Model();
        model.parseInput("tell [ 1 2 3 ]");
        model.parseInput("fd id");
        for (int i = 1; i < 4; i++) {
            assertEquals(3, model.getTurtleY(i), DELTA);
        }
    }

    @Test
    public void testTELL () {
        Model model = new Model();
        model.parseInput("tell [ 1 2 3 ]");
        model.parseInput("fd 3");
        model.parseInput("fd tell [ 4 ]");

        assertEquals(4, model.getTurtleY(4), DELTA);
        for (int i = 1; i < 4; i++) {
            assertEquals(3, model.getTurtleY(i), DELTA);
        }
    }

    @Test
    public void testTELLEVEN () {
        Model model = new Model();
        model.parseInput("tell [ 1 2 3 ]");
        model.parseInput("fd 3");
        model.parseInput("fd telleven");

        assertEquals(5, model.getTurtleY(2), DELTA);
        for (int i = 1; i < 4; i += 2) {
            assertEquals(3, model.getTurtleY(i), DELTA);
        }
    }

    @Test
    public void testTELLODD () {
        Model model = new Model();
        model.parseInput("tell [ 1 2 3 ]");
        model.parseInput("fd 3");
        model.parseInput("fd tellodd");

        assertEquals(3, model.getTurtleY(2), DELTA);
        for (int i = 1; i < 4; i += 2) {
            assertEquals(6, model.getTurtleY(i), DELTA);
        }
    }

    @Test
    public void testASK () {
        Model model = new Model();
        model.parseInput("tell [ 1 2 3 4 ]");
        model.parseInput("ask [ 1 3 ] [ fd 10 ]");
        model.parseInput("fd 5");

        for (int i = 1; i < 5; i += 2) {
            assertEquals(15, model.getTurtleY(i), DELTA);
            assertEquals(5, model.getTurtleY(i + 1), DELTA);
        }
    }

    @Test
    public void testASKWITH () {
        Model model = new Model();
        model.parseInput("tell [ 1 ]");
        model.parseInput("fd 20");
        model.parseInput("tell [ 2 ]");
        model.parseInput("fd 10");
        model.parseInput("askwith [ lessp ycor 15 ] [ fd 15 ]");

        assertEquals(20, model.getTurtleY(1), DELTA);
        assertEquals(25, model.getTurtleY(2), DELTA);
    }
}
