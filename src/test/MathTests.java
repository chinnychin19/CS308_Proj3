package test;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class MathTests {

    private static final double DELTA = 0.001;

    @Test
    public void testSUM () {
        Model.initModel();
        Model.parseInput("fd + 1 2");
        Model.parseInput("fd sum 1 2");

        Model.processNextInstruction();
        assertEquals(3, Model.getTurtle().getY(), DELTA);
        Model.processNextInstruction();
        assertEquals(6, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testDIFFERENCE () {
        Model.initModel();
        Model.parseInput("fd - 2 1");
        Model.parseInput("fd difference 1 2");

        Model.processNextInstruction();
        assertEquals(1, Model.getTurtle().getY(), DELTA);
        Model.processNextInstruction();
        assertEquals(0, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testPRODUCT () {
        Model.initModel();
        Model.parseInput("fd * 2 2");
        Model.parseInput("fd product 2 2");

        Model.processNextInstruction();
        assertEquals(4, Model.getTurtle().getY(), DELTA);
        Model.processNextInstruction();
        assertEquals(8, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testATAN () {
        Model.initModel();
        Model.parseInput("fd atan 0");

        Model.processNextInstruction();
        assertEquals(0, Model.getTurtle().getY(), DELTA);
    }
}
