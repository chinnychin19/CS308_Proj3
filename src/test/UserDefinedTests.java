package test;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class UserDefinedTests {
    private static final double DELTA = 0.001;

    @Test
    public void testMAKE () {
        Model model = new Model();
        model.parseInput("make :var1 17");
        model.parseInput("make :var2 12.5");
        model.parseInput("fd sum :var1 :var2");
        assertEquals(29.5, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testOverrideMAKE () {
        Model model = new Model();
        model.parseInput("make :var1 17");
        model.parseInput("make :var1 12.5"); // overrides :var1 to be 12.5
        model.parseInput("fd sum :var1 :var2"); // :var 1 is unset, so default is 0
        assertEquals(12.5, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testExpression () {
        Model model = new Model();
        model.parseInput("make :var1 sum 3 7");
        model.parseInput("fd :var1");
        assertEquals(10, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testFORExpression () {
        Model model = new Model();
        model.parseInput("make :var1 for [ :v 0 5 1 ] [ sum 10 :v ]");
        model.parseInput("fd :var1");
        assertEquals(15, model.getTurtleY(1), DELTA);
    }

    @Test
    public void testCallImmediately () {
        Model model = new Model();
        model.parseInput("make :var1 17 fd :var1");
        assertEquals(17, model.getTurtleY(1), DELTA);
    }
}
