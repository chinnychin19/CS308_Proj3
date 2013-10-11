package test;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class BoolTests {

    private static final double DELTA = 0.001;

    @Test
    public void testLESSP () {
        Model.initModel();
        Model.parseInput("fd less? 1 2");
        Model.parseInput("fd lessp 2 1");

        Model.processNextInstruction();
        assertEquals(1, Model.getTurtle().getY(), DELTA);
        Model.processNextInstruction();
        assertEquals(1, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testGREATERP () {
        Model.initModel();
        Model.parseInput("fd greater? 1 2");
        Model.parseInput("fd greaterp 2 1");

        Model.processNextInstruction();
        assertEquals(0, Model.getTurtle().getY(), DELTA);
        Model.processNextInstruction();
        assertEquals(1, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testEQUALP () {
        Model.initModel();
        Model.parseInput("fd equal? 1 2");
        Model.parseInput("fd equalp 2 2");

        Model.processNextInstruction();
        assertEquals(0, Model.getTurtle().getY(), DELTA);
        Model.processNextInstruction();
        assertEquals(1, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testNOTEQUALP () {
        Model.initModel();
        Model.parseInput("fd notequal? 1 2");
        Model.parseInput("fd notequalp 2 2");

        Model.processNextInstruction();
        assertEquals(1, Model.getTurtle().getY(), DELTA);
        Model.processNextInstruction();
        assertEquals(1, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testAND () {
        Model.initModel();
        Model.parseInput("fd and 1 2");
        Model.parseInput("fd and 1 0");

        Model.processNextInstruction();
        assertEquals(1, Model.getTurtle().getY(), DELTA);
        Model.processNextInstruction();
        assertEquals(1, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testOR () {
        Model.initModel();
        Model.parseInput("fd or 1 2");
        Model.parseInput("fd or 1 0");
        Model.parseInput("fd or 0 0");

        Model.processNextInstruction();
        assertEquals(1, Model.getTurtle().getY(), DELTA);
        Model.processNextInstruction();
        assertEquals(2, Model.getTurtle().getY(), DELTA);
        Model.processNextInstruction();
        assertEquals(2, Model.getTurtle().getY(), DELTA);
    }

    @Test
    public void testNOT () {
        Model.initModel();
        Model.parseInput("fd not 1");
        Model.parseInput("fd not 0");

        Model.processNextInstruction();
        assertEquals(0, Model.getTurtle().getY(), DELTA);
        Model.processNextInstruction();
        assertEquals(1, Model.getTurtle().getY(), DELTA);
    }
}
