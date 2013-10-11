package test;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class ComboTest {
    private final double DELTA = 0.001;

    @Test
    public void testFDSUM () {
        Model.initModel();
        Model.parseInput("fd sum sum 3 4 sum 5 5");
        Model.processNextInstruction();
        assertEquals(17, Model.getTurtleY(), DELTA);
    }

    @Test
    public void testRTFD () {
        Model.initModel();
        Model.parseInput("rt fd rt fd rt fd rt fd 90");
        Model.processNextInstruction();
        assertEquals(0, Model.getTurtleX(), DELTA);
        assertEquals(0, Model.getTurtleY(), DELTA);
        assertEquals(90, Model.getTurtleAngle(), DELTA);
        assertEquals(4, Model.getTurtlePaths().size());
    }

    @Test
    public void testTOFOR () {
        Model.initModel();
        Model.parseInput("to simpleMove [ :step ] [ for [ :tick 1 6 1 ] [ fd sum :tick :step ] ]");
        Model.processNextInstruction();
        Model.parseInput("simpleMove 2");
        Model.processNextInstruction();
        assertEquals(33, Model.getTurtleY(), DELTA);
    }

    @Test
    public void testFDREMAINDER () {
        Model.initModel();
        Model.parseInput("fd remainder 5 remainder remainder 10 6 5");
        Model.processNextInstruction();
        assertEquals(1, Model.getTurtleY(), DELTA);
    }

    @Test
    public void testComplicated () {
        Model.initModel();
        Model.parseInput("to complicatedFunc [ :step :stepTwo ] [ ifelse sum :step :stepTwo [] [] ]");
        Model.processNextInstruction();
        Model.parseInput("simpleMove 2");
        Model.processNextInstruction();
        assertEquals(33, Model.getTurtleY(), DELTA);
    }
}
