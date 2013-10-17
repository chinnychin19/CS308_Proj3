package test;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class ConditionalTests {

    private static final double DELTA = 0.0001;

    @Test
    public void testIF () {
        Model model = new Model();
        model.parseInput("if 1 [ fd 10 ]");
        assertEquals(10, model.getTurtleY(1), DELTA);
        model.parseInput("if 0 [ fd 10 ]");
        assertEquals(10, model.getTurtleY(1), DELTA);
        model.parseInput("if sum 1 2 [ fd 10 ]");
        assertEquals(20, model.getTurtleY(1), DELTA);
        model.parseInput("fd if 1 [ if 1 [ fd 10 ] ]");
        assertEquals(40, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testIFELSE () {
        Model model = new Model();
        model.parseInput("ifelse 1 [ fd 10 ] [ fd 20 ]");
        assertEquals(10, model.getTurtleY(1), DELTA);
        model.parseInput("ifelse 0 [ fd 10 ] [ fd 20 ]");
        assertEquals(30, model.getTurtleY(1), DELTA);
        model.parseInput("fd ifelse sum 1 2 [ fd 10 ] [ fd 20 ]");
        assertEquals(50, model.getTurtleY(1), DELTA);
        model.parseInput("ifelse 1 [ ifelse 1 [ fd 10 ] [ fd 20 ] ] [ fd 20 ]");
        assertEquals(60, model.getTurtleY(1), DELTA);
    }
}
