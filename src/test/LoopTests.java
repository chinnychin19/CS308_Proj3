package test;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class LoopTests {
    private static final double DELTA = 0.001;

    @Test
    public void testWithVariable () {
        Model.initModel();
        Model.parseInput("for [ :tick 1 6 1 ] [ fd :tick ]");
        Model.processNextInstruction();
        assertEquals(21, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testReturnValue () {
        Model.initModel();
        Model.parseInput("fd for [ :tick 1 6 1 ] [ sum :tick :tick ]");
        Model.processNextInstruction();
        // last sum is 6+6=12
        assertEquals(12, Model.getTurtle().getY(), DELTA);
    }
}
