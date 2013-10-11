package test;

import static org.junit.Assert.*;
import java.util.HashMap;
import model.Model;
import org.junit.Test;


public class UserCommandTest {
    private static final double DELTA = 0.001;

    @Test
    public void testSimple () {
        Model.initModel();
        Model.parseInput("TO rookMove [ :step ] [ fd :step fd :step rt 90 fd :step lt 90 ]");
        Model.processNextInstruction();
        assertEquals(0, Model.getTurtle().getY(), DELTA);
        Model.parseInput("rookMove 5");
        Model.processNextInstruction();
        assertEquals(10, Model.getTurtle().getY(), DELTA);
        assertEquals(5, Model.getTurtle().getX(), DELTA);
        assertEquals(false, Model.hasNextInstruction());
    }

    @Test
    public void testNested () {
        Model.initModel();
        Model.parseInput("TO rookMove [ :step ] [ fd :step fd :step rt 90 fd :step lt 90 ]");
        Model.parseInput("TO secondMove [ :stepd :rotate ] [ rookMove :stepd fd :stepd rt :rotate fd :stepd lt :rotate ]");
        // TODO: if variable name same as before, will call original value. If new name, will use
        // original :step value when calling rookMove
        Model.processNextInstruction();
        Model.processNextInstruction();
        Model.parseInput("rookMove 5");
        Model.parseInput("secondMove 3 90");
        assertEquals(0, Model.getTurtle().getY(), DELTA);
        Model.processNextInstruction();
        assertEquals(10, Model.getTurtle().getY(), DELTA);
        assertEquals(5, Model.getTurtle().getX(), DELTA);
        Model.processNextInstruction();
        assertEquals(19, Model.getTurtle().getY(), DELTA);
        assertEquals(11, Model.getTurtle().getX(), DELTA);
        assertEquals(false, Model.hasNextInstruction());
    }

    @Test
    public void testNoParams () {
        Model.initModel();
        Model.parseInput("TO square [  ] [ fd 10 rt 90 fd 10 rt 90 fd 10 rt 90 fd 10 rt 90 ]");
        Model.processNextInstruction();
        Model.parseInput("square");
        Model.processNextInstruction();
        assertEquals(0, Model.getTurtle().getX(), DELTA);
        assertEquals(0, Model.getTurtle().getY(), DELTA);
        assertEquals(90, Model.getTurtle().getAngle(), DELTA);
    }

    @Test
    public void testCommandCache () {
        HashMap<String, String> myCommandCache;
        Model.initModel();
        Model.parseInput("TO rookMove [ :step ] [ fd :step ]");
        Model.parseInput("TO secondMove [ :hello :hi ] [ rt :hello ]");
        Model.processNextInstruction();
        Model.processNextInstruction();
        myCommandCache = (HashMap<String, String>) Model.getAllCommands();

        for (String myKey : myCommandCache.keySet()) {
            System.out.println("Key: " + myKey);
            System.out.println("Value: " + myCommandCache.get(myKey));
        }
    }

}
