package test.french;

import static org.junit.Assert.*;
import java.util.HashMap;
import model.Model;
import org.junit.Test;


public class UserCommandTest {
    private static final double DELTA = 0.001;

    @Test
    public void testSimple () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("a rookMove [ :step ] [ dev :step dev :step dr 90 dev :step gc 90 ]");
        assertEquals(0, model.getTurtleY(1), DELTA);
        model.parseInput("rookMove 5");
        assertEquals(10, model.getTurtleY(1), DELTA);
        assertEquals(5, model.getTurtleX(1), DELTA);
    }

    @Test
    public void testNoParams () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("a square [  ] [ dev 10 dr 90 dev 10 dr 90 dev 10 dr 90 dev 10 dr 90 ]");
        model.parseInput("square");
        assertEquals(0, model.getTurtleX(1), DELTA);
        assertEquals(0, model.getTurtleY(1), DELTA);
        assertEquals(90, model.getTurtleAngle(1), DELTA);
    }

    @Test
    public void testCallImmediately () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("a simpleMove [ :step ] [ dev :step ] simpleMove 2");
        assertEquals(2, model.getTurtleY(1), DELTA);
    }

}
