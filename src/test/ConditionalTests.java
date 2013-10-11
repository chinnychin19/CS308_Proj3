package test;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class ConditionalTests {

    private static final double DELTA = 0.0001;

    @Test
    public void testIF () {
        Model.initModel();
        Model.parseInput("if 1 [ fd 10 ]");
        Model.parseInput("if 0 [ fd 10 ]");
        Model.parseInput("if sum 1 2 [ fd 10 ]");
        Model.parseInput("fd if 1 [ if 1 [ fd 10 ] ]");
        Model.processNextInstruction();
        assertEquals(10, Model.getTurtleY(), DELTA);
        Model.processNextInstruction();
        assertEquals(10, Model.getTurtleY(), DELTA);
        Model.processNextInstruction();
        assertEquals(20, Model.getTurtleY(), DELTA);
        Model.processNextInstruction();
        assertEquals(40, Model.getTurtleY(), DELTA);
    }

    @Test
    public void testIFELSE () {
        Model.initModel();
        Model.parseInput("ifelse 1 [ fd 10 ] [ fd 20 ]");
        Model.parseInput("ifelse 0 [ fd 10 ] [ fd 20 ]");
        Model.parseInput("fd ifelse sum 1 2 [ fd 10 ] [ fd 20 ]");
        Model.parseInput("ifelse 1 [ ifelse 1 [ fd 10 ] [ fd 20 ] ] [ fd 20 ]");
        Model.processNextInstruction();
        assertEquals(10, Model.getTurtleY(), DELTA);
        Model.processNextInstruction();
        assertEquals(30, Model.getTurtleY(), DELTA);
        Model.processNextInstruction();
        assertEquals(50, Model.getTurtleY(), DELTA);
        Model.processNextInstruction();
        assertEquals(60, Model.getTurtleY(), DELTA);
    }
}
