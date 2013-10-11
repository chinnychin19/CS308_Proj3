package test;

import static org.junit.Assert.*;
import model.Model;
import model.instruction.error.DivideByZero;
import model.instruction.error.InvalidCommandInstruction;
import model.instruction.error.LogOfZero;
import model.instruction.error.NonPositiveRandom;
import model.instruction.error.TanOfNinety;
import model.instruction.error.TooFewParametersInstruction;
import org.junit.Test;


public class ErrorTests {

    @Test
    public void testSimpleDBZ () {
        Model.initModel();
        String ret = Model.parseInput("/ 5 0");
        assertEquals(DivideByZero.MESSAGE, ret);
        ret = Model.parseInput("% 5 0");
        assertEquals(DivideByZero.MESSAGE, ret);
    }

    @Test
    public void testComplexDBZ () {
        Model.initModel();
        String ret = Model.parseInput("sum 3 / 5 0");
        assertEquals(DivideByZero.MESSAGE, ret);
        ret = Model.parseInput("sum 3 % 5 0");
        assertEquals(DivideByZero.MESSAGE, ret);
    }

    @Test
    public void testSimpleLogZero () {
        Model.initModel();
        String ret = Model.parseInput("log 0");
        assertEquals(LogOfZero.MESSAGE, ret);
    }

    @Test
    public void testComplexLogZero () {
        Model.initModel();
        String ret = Model.parseInput("log log sum -8 sum 3 5");
        assertEquals(LogOfZero.MESSAGE, ret);
    }

    @Test
    public void testSimpleTanNinety () {
        Model.initModel();
        String ret = Model.parseInput("tan 90");
        assertEquals(TanOfNinety.MESSAGE, ret);
    }

    @Test
    public void testComplexTanNinety () {
        Model.initModel();
        String ret = Model.parseInput("log log sum 3 tan sum 45 45");
        assertEquals(TanOfNinety.MESSAGE, ret);
    }

    @Test
    public void testSimpleNegativeRandom () {
        Model.initModel();
        String ret = Model.parseInput("random -3");
        assertEquals(NonPositiveRandom.MESSAGE, ret);
        ret = Model.parseInput("random 0");
        assertEquals(NonPositiveRandom.MESSAGE, ret);
    }

    @Test
    public void testComplexNegativeRandom () {
        Model.initModel();
        String ret = Model.parseInput("+ 5 + random -5 random -5");
        assertEquals(NonPositiveRandom.MESSAGE, ret);
    }

    @Test
    public void testInvalidCommand() {
        Model.initModel();
        String ret = Model.parseInput("hakuna 17");
        assertEquals(InvalidCommandInstruction.MESSAGE, ret);
    }

    @Test
    public void testTooFewParameters() {
        Model.initModel();
        String ret = Model.parseInput("sum sum sum 3 4");
        assertEquals(TooFewParametersInstruction.MESSAGE, ret);
    }
}
