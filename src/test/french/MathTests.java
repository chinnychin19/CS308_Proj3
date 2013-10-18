package test.french;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class MathTests {

    private static final double DELTA = 0.001;

    @Test
    public void testSOMME () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("dev + 1 2");
        assertEquals(3, model.getTurtleY(1), DELTA);
        model.parseInput("dev somme 1 2");
        assertEquals(6, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testDIFFERENCE () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("dev - 2 1"); // forward 1
        assertEquals(1, model.getTurtleY(1), DELTA);
        model.parseInput("dev difference 1 2"); // forward -1
        assertEquals(0, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testPRODUIT () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("dev * 2 2");
        assertEquals(4, model.getTurtleY(1), DELTA);
        model.parseInput("dev produit 2 2");
        assertEquals(8, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testQUOTIENT () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("dev / 8 2");
        assertEquals(4, model.getTurtleY(1), DELTA);
        model.parseInput("dev quotient 8 2");
        assertEquals(8, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testRESTE () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("dev % 11 4");
        assertEquals(3, model.getTurtleY(1), DELTA);
        model.parseInput("dev reste 11 4");
        assertEquals(6, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testMOINS () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("dev ~ 4");
        assertEquals(-4, model.getTurtleY(1), DELTA);
        model.parseInput("dev moins 4");
        assertEquals(-8, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testALEATOIRE () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("dev aleatoire 4");
        assertTrue(model.getTurtleY(1) >= 0 && model.getTurtleY(1) <= 4);
    }

    @Test
    public void testSIN () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("dev sin 90");
        assertEquals(1, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testCOS () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("dev cos 180");
        assertEquals(-1, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testTAN () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("dev tan 45");
        assertEquals(1, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testATAN () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("dev atan 45");
        assertEquals(0.666, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testLOG () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("dev log 2.718");
        assertEquals(1, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testPOW () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("dev pow 3 2");
        assertEquals(9, model.getTurtleY(1), DELTA);
    }
}
