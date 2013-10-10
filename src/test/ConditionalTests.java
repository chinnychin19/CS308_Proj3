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
        // TODO: Finish test
        Model.initModel();
        Model.parseInput("if 1 [ fd 10 ]");
        Model.parseInput("if 0 [ fd 10 ]");
        Model.parseInput("if sum 1 2 [ fd 10 ]");
        Model.processNextInstruction();
        assertEquals(10, Model.getTurtleY(), DELTA);
        Model.processNextInstruction();
        assertEquals(10, Model.getTurtleY(), DELTA);
        Model.processNextInstruction();
        assertEquals(20, Model.getTurtleY(), DELTA);
    }

}
