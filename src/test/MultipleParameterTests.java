package test;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class MultipleParameterTests {

    private static final double DELTA = 0.001;

    @Test
    public void simpleTest () {
        Model model = new Model();
        model.parseInput("fd ( sum 3 4 5 )");
        assertEquals(12, model.getTurtleY(1), DELTA);
        model.parseInput("fd ( difference 6 2 1 )");
        assertEquals(15, model.getTurtleY(1), DELTA);
        model.parseInput("fd ( product 2 2 2 )");
        assertEquals(23, model.getTurtleY(1), DELTA);
        model.parseInput("fd ( and 6 2 1 )");
        assertEquals(24, model.getTurtleY(1), DELTA);
        model.parseInput("fd ( and 0 2 1 )");
        assertEquals(24, model.getTurtleY(1), DELTA);
        model.parseInput("fd ( or 6 2 1 )");
        assertEquals(25, model.getTurtleY(1), DELTA);
        model.parseInput("fd ( or 0 0 0 )");
        assertEquals(25, model.getTurtleY(1), DELTA);
    }

    @Test
    public void consecutiveTest () {
        Model model = new Model();
        model.parseInput("fd sum ( sum 3 4 5 ) ( sum 4 5 6 )");
        assertEquals(27, model.getTurtleY(1), DELTA);
    }

    @Test
    public void nestedTest () {
        // TODO: Determine cause of invalid command. See garbageTest
        Model model = new Model();
        model.parseInput("fd ( sum 3 4 ( sum 2 3 4 ) )");
        assertEquals(16, model.getTurtleY(1), DELTA);
    }
}
