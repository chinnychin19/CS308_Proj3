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
        Model.parseInput("fd - 2 1"); // forward 1
        Model.parseInput("fd difference 1 2"); // forward -1

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
    public void testQUOTIENT () {
        Model.initModel();
        Model.parseInput("fd / 8 2");
        Model.parseInput("fd quotient 8 2");

        Model.processNextInstruction();
        assertEquals(4, Model.getTurtle().getY(), DELTA);
        Model.processNextInstruction();
        assertEquals(8, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testREMAINDER () {
        Model.initModel();
        Model.parseInput("fd % 11 4");
        Model.parseInput("fd remainder 11 4");

        Model.processNextInstruction();
        assertEquals(3, Model.getTurtle().getY(), DELTA);
        Model.processNextInstruction();
        assertEquals(6, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testMINUS () {
        Model.initModel();
        Model.parseInput("fd ~ 4");
        Model.parseInput("fd minus 4");

        Model.processNextInstruction();
        assertEquals(-4, Model.getTurtle().getY(), DELTA);
        Model.processNextInstruction();
        assertEquals(-8, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testRANDOM () {
        Model.initModel();
        Model.parseInput("fd random 4");

        Model.processNextInstruction();
        assertTrue(Model.getTurtle().getY() >= 0 && Model.getTurtle().getY() <= 4);
    }

    @Test
    public void testSIN () {
        Model.initModel();
        Model.parseInput("fd sin 90");

        Model.processNextInstruction();
        assertEquals(1, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testCOS () {
        Model.initModel();
        Model.parseInput("fd cos 180");

        Model.processNextInstruction();
        assertEquals(-1, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testTAN () {
        Model.initModel();
        Model.parseInput("fd tan 45");

        Model.processNextInstruction();
        assertEquals(1, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testATAN () {
        Model.initModel();
        Model.parseInput("fd atan 45");

        Model.processNextInstruction();
        assertEquals(0.666, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testLOG () {
        Model.initModel();
        Model.parseInput("fd log 2.718");

        Model.processNextInstruction();
        assertEquals(1, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testPOW () {
        Model.initModel();
        Model.parseInput("fd pow 3 2");

        Model.processNextInstruction();
        assertEquals(9, Model.getTurtle().getY(), DELTA);
    }
}
