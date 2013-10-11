package test;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class UserCommandTest {
    private static final double DELTA = 0.001;

    @Test
    public void testSimple () {
        Model.initModel();
        Model.parseInput("TO rookMove [ :step ] [ fd :step fd :step rt 90 fd :step lt 90 ]");
        Model.processNextInstruction();
        assertEquals(0, Model.getTurtle().getY(), DELTA);
        Model.parseInput("rookMove 5");
        Model.processNextInstruction();
        assertEquals(10, Model.getTurtle().getY(), DELTA);
        assertEquals(5, Model.getTurtle().getX(), DELTA);
        assertEquals(false, Model.hasNextInstruction());
    }

}
