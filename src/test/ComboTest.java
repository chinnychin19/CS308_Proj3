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
    public void testTRFD () {
        Model.initModel();
        Model.parseInput("rt fd rt fd rt fd rt fd 90");
        Model.processNextInstruction();
        assertEquals(0, Model.getTurtleX(), DELTA);
        assertEquals(0, Model.getTurtleY(), DELTA);
        assertEquals(90, Model.getTurtleAngle(), DELTA);
        assertEquals(4, Model.getTurtlePaths().size());
    }
}
