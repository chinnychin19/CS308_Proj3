package test;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class ComboTest {
    private final double DELTA = 0.001;

    @Test
    public void testFDSUM () {
        Model model = new Model();
        model.parseInput("fd sum sum 3 4 sum 5 5");
        assertEquals(17, model.getTurtleY(), DELTA);
    }

    @Test
    public void testRTFD () {
        Model model = new Model();
        model.parseInput("rt fd rt fd rt fd rt fd 90");
        assertEquals(0, model.getTurtleX(), DELTA);
        assertEquals(0, model.getTurtleY(), DELTA);
        assertEquals(90, model.getTurtleAngle(), DELTA);
        assertEquals(4, model.getTurtlePaths().size());
    }

    @Test
    public void testTOFOR () {
        Model model = new Model();
        model.parseInput("to simpleMove [ :step ] [ for [ :tick 1 6 1 ] [ fd sum :tick :step ] ]");
        model.parseInput("simpleMove 2");
        assertEquals(33, model.getTurtleY(), DELTA);
    }

    @Test
    public void testFDREMAINDER () {
        Model model = new Model();
        model.parseInput("fd remainder 5 remainder remainder 10 6 5");
        assertEquals(1, model.getTurtleY(), DELTA);
    }

    @Test
    public void testComplicated () {
        Model model = new Model();
        model.parseInput("to square [ ] [ fd 10 rt 90 fd 10 rt 90 fd 10 rt 90 fd 10 rt 90 ]");
        model.parseInput("to rookMove [ :step ] [ fd :step fd :step rt 90 fd :step lt 90 ]");
        model.parseInput("make :a 3");
        model.parseInput("make :b 2");
        model.parseInput("ifelse sum :a :b [ square fd 10 ] [ rookMove :a fd :b to newMove [ :tick ] [ fd :tick fd sum :a :b ] newMove 2 ]");

        assertEquals(10, model.getTurtle().getY(), DELTA);

    }
}
