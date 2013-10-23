package test;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class UserCommandTest {
    private static final double DELTA = 0.001;

    @Test
    public void testSimple () {
        Model model = new Model();
        String status =
                model.parseInput("to rookMove [ :step ] [ fd :step fd :step rt 90 fd :step lt 90 ]");
        System.out.println(status);
        assertEquals(0, model.getTurtleY(1), DELTA);
        model.parseInput("rookMove 5");
        assertEquals(10, model.getTurtleY(1), DELTA);
        assertEquals(5, model.getTurtleX(1), DELTA);
    }

    @Test
    public void testNested () {
        Model model = new Model();
        model.parseInput("to rookMove [ :step ] [ fd :step fd :step ]");
        model.parseInput("to secondMove [ :step2 :rotate ] [ rookMove :step2 rt :rotate rookMove :step2 ]");
        model.parseInput("rookMove 5");
        assertEquals(10, model.getTurtleY(1), DELTA);
        model.parseInput("secondMove 3 90");
        assertEquals(16, model.getTurtleY(1), DELTA);
        assertEquals(6, model.getTurtleX(1), DELTA);
    }

    @Test
    public void testNoParams () {
        Model model = new Model();
        model.parseInput("to square [  ] [ fd 10 rt 90 fd 10 rt 90 fd 10 rt 90 fd 10 rt 90 ]");
        model.parseInput("square");
        assertEquals(0, model.getTurtleX(1), DELTA);
        assertEquals(0, model.getTurtleY(1), DELTA);
        assertEquals(90, model.getTurtleAngle(1), DELTA);
    }

    @Test
    public void testCallImmediately () {
        Model model = new Model();
        System.out.println(model.parseInput("to simpleMove [ :step ] [ fd :step ] simpleMove 2"));
        assertEquals(2, model.getTurtleY(1), DELTA);
    }

    // @Test
    // public void testCommandInLoop () {
    // Model2 model = new Model2();
    // model.parseInput("to fun [ :f ] [ repeat 5 [ fd :f rt 36 ] ]");
    // model.parseInput("repeat 10 [ fun * :repcount 10 ]");
    // assertNotEquals(0, model.getTurtleX(1), DELTA);
    // assertNotEquals(0, model.getTurtleY(1), DELTA);
    // }

}
