package test;

import static org.junit.Assert.*;
import model.Model;
import model.instruction.error.DivideByZeroError;
import org.junit.Test;


public class ErrorTests {

    @Test
    public void testSimpleDBZ () {
        Model.initModel();
        String ret = Model.parseInput("/ 5 0");
        assertEquals(DivideByZeroError.MESSAGE, ret);
    }

    @Test
    public void testComplexDBZ () {
        Model.initModel();
        String ret = Model.parseInput("sum 3 / 5 0");
        assertEquals(DivideByZeroError.MESSAGE, ret);
    }
}
