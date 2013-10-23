package test;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class StackTests {
    public static final double DELTA = 0.001;

    @Test
    public void testSimple () {
        Model model = new Model();
        assertFalse(model.canUndo());
        assertFalse(model.canRedo());
        model.parseInput("fd 10");
        model.parseInput("fd 10");
        assertEquals(20, model.getTurtleY(1), DELTA);
        assertTrue(model.canUndo());
        assertFalse(model.canRedo());
        model.undo();
        assertEquals(10, model.getTurtleY(1), DELTA);
        assertTrue(model.canUndo());
        assertTrue(model.canRedo());
        model.redo();
        assertEquals(20, model.getTurtleY(1), DELTA);
        assertTrue(model.canUndo());
        assertFalse(model.canRedo());
        model.parseInput("rt 90");
        assertEquals(0, model.getTurtleAngle(1), DELTA);
        model.undo();
        assertEquals(90, model.getTurtleAngle(1), DELTA);
        assertTrue(model.canUndo());
        assertTrue(model.canRedo());
        model.parseInput("fd 10");
        assertTrue(model.canUndo());
        assertFalse(model.canRedo());
    }

    @Test
    public void testMultipleTurtles () {
        Model model = new Model();
        model.parseInput("tell [ 1 2 3 ]");
        model.parseInput("fd 10");
        model.parseInput("fd 10");
        for (int i = 1; i < 4; i++) {
            assertEquals(20, model.getTurtleY(i), DELTA);
        }
        model.undo();
        for (int i = 1; i < 4; i++) {
            assertEquals(10, model.getTurtleY(i), DELTA);
        }
        model.parseInput("tell [ 1 ]");
        model.parseInput("fd 10");
        model.undo();
        for (int i = 1; i < 4; i++) {
            assertEquals(10, model.getTurtleY(i), DELTA);
        }
        model.redo();
        assertEquals(20, model.getTurtleY(1), DELTA);
        for (int i = 2; i < 4; i++) {
            assertEquals(10, model.getTurtleY(i), DELTA);
        }
    }
}
