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
        while (Model.hasNextInstruction()) {
            Model.processNextInstruction();
        }
        assertEquals(29.5, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testOverrideMAKE () {
        Model.initModel();
        Model.parseInput("make :var1 17");
        Model.parseInput("make :var1 12.5"); // overrides :var1 to be 12.5
        Model.parseInput("fd sum :var1 :var2"); // :var 1 is unset, so default is 0
        while (Model.hasNextInstruction()) {
            Model.processNextInstruction();
        }
        assertEquals(12.5, Model.getTurtle().getY(), DELTA);
    }
}
