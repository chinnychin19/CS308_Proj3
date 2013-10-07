package test;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class ModelTests {

    @Test
    public void testCommandHistory () {
        Model.initModel();
        String[] testString = { "fd 10", "fd 10", "fd 10" };
        for (int i = 0; i < testString.length; i++) {
            Model.parseInput(testString[i]);
            assertEquals("String " + i, testString[i], Model.getHistory().get(i));
        }
    }

    @Test
    public void testInstructionQueue () {
        Model.initModel();
        assertEquals("Before anything added", false, Model.hasNextInstruction());
        Model.parseInput("fd 10");
        assertEquals("Add one instruction", true, Model.hasNextInstruction());
        Model.processNextInstruction();
        assertEquals("After process instruction", false, Model.hasNextInstruction());
    }

}
