package test;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class UserDefinedTests {
    private static final double DELTA = 0.001;

    @Test
    public void testMAKE () {
        Model.initModel();
        Model.parseInput("make :var1 17");
        Model.parseInput("make :var2 12.5");
        Model.parseInput("fd sum :var1 :var2");
        assertEquals(29.5, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testOverrideMAKE () {
        Model.initModel();
        Model.parseInput("make :var1 17");
        Model.parseInput("make :var1 12.5"); // overrides :var1 to be 12.5
        Model.parseInput("fd sum :var1 :var2"); // :var 1 is unset, so default is 0
        assertEquals(12.5, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testExpression () {
        Model.initModel();
        Model.parseInput("make :var1 sum 3 7");
        Model.parseInput("fd :var1");
        assertEquals(10, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testFORExpression () {
        Model.initModel();
        Model.parseInput("make :var1 for [ :v 0 5 1 ] [ sum 10 :v ]");
        Model.parseInput("fd :var1");
        assertEquals(15, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testCallImmediately () {
        Model.initModel();
        Model.parseInput("make :var1 17 fd :var1");
        assertEquals(17, Model.getTurtle().getY(), DELTA);
    }
}
