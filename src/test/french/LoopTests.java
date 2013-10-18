package test.french;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class LoopTests {
    private static final double DELTA = 0.001;

    @Test
    public void testWithVariable () {
        Model model = new Model();
        model.parseInput("for [ :tick 1 6 1 ] [ fd :tick ]"); // 1,2,3,4,5,6
        assertEquals(21, model.getTurtleY(1), DELTA);
        model.parseInput("dotimes [ :tick 7 ] [ fd :tick ]"); // 0,1,2,3,4,5,6
        assertEquals(42, model.getTurtleY(1), DELTA);
        model.parseInput("repeat 6 [ fd :repcount ]");  // 1,2,3,4,5,6
        assertEquals(63, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testReturnValue () {
        Model model = new Model();
        model.parseInput("fd for [ :tick 1 6 1 ] [ sum :tick :tick ]");
        assertEquals(12, model.getTurtleY(1), DELTA);
        model.parseInput("fd dotimes [ :tick 7 ] [ sum :tick :tick ]");
        assertEquals(24, model.getTurtleY(1), DELTA);
        model.parseInput("fd repeat 6 [ sum :repcount :repcount ]");
        assertEquals(36, model.getTurtleY(1), DELTA);
        // last sum is 6+6=12
    }

    @Test
    public void testNestedLoops () {
        Model model = new Model();
        model.parseInput("for [ :tick 1 6 1 ] [ for [ :tickNest 1 6 1 ] [ fd sum :tick :tickNest ] ]");
        assertEquals(252, model.getTurtleY(1), DELTA);
        model.parseInput("dotimes [ :tick 7 ] [ dotimes [ :tickNest 7 ] [ fd sum :tick :tickNest ] ]");
        assertEquals(546, model.getTurtleY(1), DELTA);
        model.parseInput("home repeat sum 3 sum 2 1 [ repeat 6 [ fd :repcount ] ]");
        assertEquals(21 * 6, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testBackwards () {
        Model model = new Model();
        assertEquals(90, model.getTurtleAngle(1), DELTA);
        model.parseInput("rt for [ :tick 6 2 -1 ] [ fd sum :tick :tick ]");
        assertEquals(86, model.getTurtleAngle(1), DELTA);
        assertEquals(40, model.getTurtleY(1), DELTA);
        // last sum is 2+2=4
    }
}
