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
        Model.parseInput("dotimes [ :tick 7 ] [ fd :tick ]");
        Model.processNextInstruction();
        assertEquals(21, Model.getTurtle().getY(), DELTA);
        Model.processNextInstruction();
        assertEquals(42, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testReturnValue () {
        Model.initModel();
        Model.parseInput("fd for [ :tick 1 6 1 ] [ sum :tick :tick ]");
        Model.parseInput("fd dotimes [ :tick 7 ] [ sum :tick :tick ]");
        Model.processNextInstruction();
        // last sum is 6+6=12
        assertEquals(12, Model.getTurtle().getY(), DELTA);
        Model.processNextInstruction();
        assertEquals(24, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testNestedLoops () {
        Model.initModel();
        Model.parseInput("for [ :tick 1 6 1 ] [ for [ :tickNest 1 6 1 ] [ fd sum :tick :tickNest ] ]");
        Model.parseInput("dotimes [ :tick 7 ] [ dotimes [ :tickNest 7 ] [ fd sum :tick :tickNest ] ]");
        Model.processNextInstruction();
        assertEquals(252, Model.getTurtle().getY(), DELTA);
        Model.processNextInstruction();
        assertEquals(546, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testBackwards () {
        Model.initModel();
        assertEquals(90, Model.getTurtle().getAngle(), DELTA);
        Model.parseInput("rt for [ :tick 6 2 -1 ] [ fd sum :tick :tick ]");
        Model.processNextInstruction();
        // last sum is 2+2=4
        assertEquals(86, Model.getTurtle().getAngle(), DELTA);
        assertEquals(40, Model.getTurtle().getY(), DELTA);
    }
}
