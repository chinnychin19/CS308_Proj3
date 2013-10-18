package test.french;

import static org.junit.Assert.*;
import model.Model;
import model.instruction.error.DivideByZero;
import model.instruction.error.InfiniteLoop;
import model.instruction.error.InvalidCommandInstruction;
import model.instruction.error.LogOfZero;
import model.instruction.error.NonPositiveRandom;
import model.instruction.error.TanOfNinety;
import model.instruction.error.TooFewParametersInstruction;
import org.junit.Test;


public class ErrorTests {

    @Test
    public void testSimpleDBZ () {
        Model model = new Model();
        String ret = model.parseInput("/ 5 0");
        assertEquals(DivideByZero.MESSAGE, ret);
        ret = model.parseInput("% 5 0");
        assertEquals(DivideByZero.MESSAGE, ret);
    }

    @Test
    public void testComplexDBZ () {
        Model model = new Model();
        String ret = model.parseInput("sum 3 / 5 0");
        assertEquals(DivideByZero.MESSAGE, ret);
        ret = model.parseInput("sum 3 % 5 0");
        assertEquals(DivideByZero.MESSAGE, ret);
    }

    @Test
    public void testSimpleLogZero () {
        Model model = new Model();
        String ret = model.parseInput("log 0");
        assertEquals(LogOfZero.MESSAGE, ret);
    }

    @Test
    public void testComplexLogZero () {
        Model model = new Model();
        String ret = model.parseInput("log log sum -8 sum 3 5");
        assertEquals(LogOfZero.MESSAGE, ret);
    }

    @Test
    public void testSimpleTanNinety () {
        Model model = new Model();
        String ret = model.parseInput("tan 90");
        assertEquals(TanOfNinety.MESSAGE, ret);
    }

    @Test
    public void testComplexTanNinety () {
        Model model = new Model();
        String ret = model.parseInput("log log sum 3 tan sum 45 45");
        assertEquals(TanOfNinety.MESSAGE, ret);
    }

    @Test
    public void testSimpleNegativeRandom () {
        Model model = new Model();
        String ret = model.parseInput("random -3");
        assertEquals(NonPositiveRandom.MESSAGE, ret);
        ret = model.parseInput("random 0");
        assertEquals(NonPositiveRandom.MESSAGE, ret);
    }

    @Test
    public void testComplexNegativeRandom () {
        Model model = new Model();
        String ret = model.parseInput("+ 5 + random -5 random -5");
        assertEquals(NonPositiveRandom.MESSAGE, ret);
    }

    @Test
    public void testInvalidCommand () {
        Model model = new Model();
        String ret = model.parseInput("hakuna 17");
        assertEquals(InvalidCommandInstruction.MESSAGE, ret);
    }

    @Test
    public void testTooFewParameters () {
        Model model = new Model();
        String ret = model.parseInput("sum sum sum 3 4");
        assertEquals(TooFewParametersInstruction.MESSAGE, ret);
    }

    @Test
    public void testInfiniteLoop () {
        Model model = new Model();
        String ret = model.parseInput("for [ :v 0 1 0 ] [ fd 10 ]");
        assertEquals(InfiniteLoop.MESSAGE, ret);
        ret = model.parseInput("for [ :v 0 1 -1 ] [ fd 10 ]");
        assertEquals(InfiniteLoop.MESSAGE, ret);
        ret = model.parseInput("for [ :v 1 0 1 ] [ fd 10 ]");
        assertEquals(InfiniteLoop.MESSAGE, ret);
    }

}
