package test;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;

public class ComboTest {

    @Test
    public void testFDSUM () { //TODO: still need to add math operations and bool ops to instruction factory
        Model.initModel();
        Model.parseInput("fd sum 3 4");
        Model.processNextInstruction();
        assertEquals(7, Model.getTurtleX());
    }

}
