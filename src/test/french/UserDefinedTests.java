package test.french;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class UserDefinedTests {
    private static final double DELTA = 0.001;

    @Test
    public void testFAIT () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("fait :var1 17");
        model.parseInput("def :var2 12.5");
        model.parseInput("dev + :var1 :var2");
        assertEquals(29.5, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testCallImmediately () {
        Model model = new Model();
        model.setLanguage("French");
        model.parseInput("def :var1 17 dev :var1");
        assertEquals(17, model.getTurtleY(1), DELTA);
    }
}
