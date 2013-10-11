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
        assertEquals(3, Model.getTurtle().getY(), DELTA);
        Model.parseInput("fd sum 1 2");
        assertEquals(6, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testDIFFERENCE () {
        Model.initModel();
        Model.parseInput("fd - 2 1"); // forward 1
        assertEquals(1, Model.getTurtle().getY(), DELTA);
        Model.parseInput("fd difference 1 2"); // forward -1
        assertEquals(0, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testPRODUCT () {
        Model.initModel();
        Model.parseInput("fd * 2 2");
        assertEquals(4, Model.getTurtle().getY(), DELTA);
        Model.parseInput("fd product 2 2");
        assertEquals(8, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testQUOTIENT () {
        Model.initModel();
        Model.parseInput("fd / 8 2");
        assertEquals(4, Model.getTurtle().getY(), DELTA);
        Model.parseInput("fd quotient 8 2");
        assertEquals(8, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testREMAINDER () {
        Model.initModel();
        Model.parseInput("fd % 11 4");
        assertEquals(3, Model.getTurtle().getY(), DELTA);
        Model.parseInput("fd remainder 11 4");
        assertEquals(6, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testMINUS () {
        Model.initModel();
        Model.parseInput("fd ~ 4");
        assertEquals(-4, Model.getTurtle().getY(), DELTA);
        Model.parseInput("fd minus 4");
        assertEquals(-8, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testRANDOM () {
        Model.initModel();
        Model.parseInput("fd random 4");
        assertTrue(Model.getTurtle().getY() >= 0 && Model.getTurtle().getY() <= 4);
    }

    @Test
    public void testSIN () {
        Model.initModel();
        Model.parseInput("fd sin 90");
        assertEquals(1, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testCOS () {
        Model.initModel();
        Model.parseInput("fd cos 180");
        assertEquals(-1, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testTAN () {
        Model.initModel();
        Model.parseInput("fd tan 45");
        assertEquals(1, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testATAN () {
        Model.initModel();
        Model.parseInput("fd atan 45");
        assertEquals(0.666, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testLOG () {
        Model.initModel();
        Model.parseInput("fd log 2.718");
        assertEquals(1, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testPOW () {
        Model.initModel();
        Model.parseInput("fd pow 3 2");
        assertEquals(9, Model.getTurtle().getY(), DELTA);
    }
}
