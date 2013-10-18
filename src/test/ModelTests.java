package test;

import static org.junit.Assert.*;
import java.util.List;
import model.Model;
import org.junit.Test;


public class ModelTests {

    @Test
    public void testCommandHistory () {
        Model model = new Model();
        String[] testString = { "fd 10", "fd 10", "fd 10" };
        for (int i = 0; i < testString.length; i++) {
            model.parseInput(testString[i]);
            assertEquals("String " + i, testString[i], model.getHistory().get(i));
        }
    }

    // TODO: Fix this test
    // @Test
    // public void testActiveTurtles () {
    // Model model = new Model();
    // model.parseInput("tell [ 1 2 3 4 ]");
    // for (int i = 0; i < 4; i++) {
    // assertEquals(((List<Integer>) model.getActiveTurtleIDs()).get(i), i + 1);
    // assertEquals(((List<Integer>) model.getAllTurtleIDs()).get(i), i + 1);
    // }
    //
    // model.parseInput("tell [ 1 3 ]");
    // for (int i = 0; i < 2; i++) {
    // assertEquals(((List<Integer>) model.getActiveTurtleIDs()).get(i), 2 * i + 1);
    // }
    // for (int i = 0; i < 4; i++) {
    // assertEquals(((List<Integer>) model.getAllTurtleIDs()).get(i), i + 1);
    // }
    // }
}
