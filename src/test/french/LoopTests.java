package test.french;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class LoopTests {
    private static final double DELTA = 0.001;

    @Test
    public void testWithVariable () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("pour [ :tick 1 6 1 ] [ dev :tick ]"); // 1,2,3,4,5,6
        assertEquals(21, model.getTurtleY(1), DELTA);
        model.parseInput("dottime* [ :tick 7 ] [ dev :tick ]"); // 0,1,2,3,4,5,6
        assertEquals(42, model.getTurtleY(1), DELTA);
        model.parseInput("repete 6 [ dev :repcount ]");  // 1,2,3,4,5,6
        assertEquals(63, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testBackwards () {
        Model model = new Model();
        model.setLanguage("French");
        assertEquals(90, model.getTurtleAngle(1), DELTA);
        model.parseInput("dr pour [ :tick 6 2 -1 ] [ dev + :tick :tick ]");
        assertEquals(86, model.getTurtleAngle(1), DELTA);
        assertEquals(40, model.getTurtleY(1), DELTA);
        // last sum is 2+2=4
    }
}
