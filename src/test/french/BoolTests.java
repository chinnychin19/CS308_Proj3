package test.french;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class BoolTests {

    private static final double DELTA = 0.001;

    @Test
    public void testINFERIEURP () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("fd inferieur? 1 2");
        assertEquals(1, model.getTurtleY(1), DELTA);
        model.parseInput("fd inferieurp 2 1");
        assertEquals(1, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testSUPERIEURP () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("fd superieur? 1 2");
        assertEquals(0, model.getTurtleY(1), DELTA);
        model.parseInput("fd superieurp 2 1");
        assertEquals(1, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testEGALP () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("fd egal? 1 2");
        assertEquals(0, model.getTurtleY(1), DELTA);
        model.parseInput("fd egalp 2 2");
        assertEquals(1, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testDIFFERENTP () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("fd different? 1 2");
        assertEquals(1, model.getTurtleY(1), DELTA);
        model.parseInput("fd differentp 2 2");
        assertEquals(1, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testET () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("fd et 1 2");
        assertEquals(1, model.getTurtleY(1), DELTA);
        model.parseInput("fd et 1 0");
        assertEquals(1, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testOU () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("fd ou 1 2");
        assertEquals(1, model.getTurtleY(1), DELTA);
        model.parseInput("fd ou 1 0");
        assertEquals(2, model.getTurtleY(1), DELTA);
        model.parseInput("fd ou 0 0");
        assertEquals(2, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testNON () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("fd non 1");
        assertEquals(0, model.getTurtleY(1), DELTA);
        model.parseInput("fd non 0");
        assertEquals(1, model.getTurtleY(1), DELTA);
    }
}
