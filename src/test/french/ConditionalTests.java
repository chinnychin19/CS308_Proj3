package test.french;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class ConditionalTests {

    private static final double DELTA = 0.0001;

    @Test
    public void testSI () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("si 1 [ dev 10 ]");
        assertEquals(10, model.getTurtleY(1), DELTA);
        model.parseInput("si 0 [ dev 10 ]");
        assertEquals(10, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testSINON () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("sinon 1 [ dev 10 ] [ dev 20 ]");
        assertEquals(10, model.getTurtleY(1), DELTA);
        model.parseInput("sinon 0 [ dev 10 ] [ dev 20 ]");
        assertEquals(30, model.getTurtleY(1), DELTA);
    }
}
