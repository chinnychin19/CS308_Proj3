package test;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class MathTests {

    private static final double DELTA = 0.001;

    @Test
    public void testSUM () {
        Model model = new Model();
        model.parseInput("fd + 1 2");
        assertEquals(3, model.getTurtle().getY(), DELTA);
        model.parseInput("fd sum 1 2");
        assertEquals(6, model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testDIFFERENCE () {
        Model model = new Model();
        model.parseInput("fd - 2 1"); // forward 1
        assertEquals(1, model.getTurtle().getY(), DELTA);
        model.parseInput("fd difference 1 2"); // forward -1
        assertEquals(0, model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testPRODUCT () {
        Model model = new Model();
        model.parseInput("fd * 2 2");
        assertEquals(4, model.getTurtle().getY(), DELTA);
        model.parseInput("fd product 2 2");
        assertEquals(8, model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testQUOTIENT () {
        Model model = new Model();
        model.parseInput("fd / 8 2");
        assertEquals(4, model.getTurtle().getY(), DELTA);
        model.parseInput("fd quotient 8 2");
        assertEquals(8, model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testREMAINDER () {
        Model model = new Model();
        model.parseInput("fd % 11 4");
        assertEquals(3, model.getTurtle().getY(), DELTA);
        model.parseInput("fd remainder 11 4");
        assertEquals(6, model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testMINUS () {
        Model model = new Model();
        model.parseInput("fd ~ 4");
        assertEquals(-4, model.getTurtle().getY(), DELTA);
        model.parseInput("fd minus 4");
        assertEquals(-8, model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testRANDOM () {
        Model model = new Model();
        model.parseInput("fd random 4");
        assertTrue(model.getTurtle().getY() >= 0 && model.getTurtle().getY() <= 4);
    }

    @Test
    public void testSIN () {
        Model model = new Model();
        model.parseInput("fd sin 90");
        assertEquals(1, model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testCOS () {
        Model model = new Model();
        model.parseInput("fd cos 180");
        assertEquals(-1, model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testTAN () {
        Model model = new Model();
        model.parseInput("fd tan 45");
        assertEquals(1, model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testATAN () {
        Model model = new Model();
        model.parseInput("fd atan 45");
        assertEquals(0.666, model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testLOG () {
        Model model = new Model();
        model.parseInput("fd log 2.718");
        assertEquals(1, model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testPOW () {
        Model model = new Model();
        model.parseInput("fd pow 3 2");
        assertEquals(9, model.getTurtle().getY(), DELTA);
    }
}
