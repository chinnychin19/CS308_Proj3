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
        Model.parseInput("to rookMove [ :step ] [ fd :step fd :step rt 90 fd :step lt 90 ]");
        assertEquals(0, Model.getTurtle().getY(), DELTA);
        Model.parseInput("rookMove 5");
        assertEquals(10, Model.getTurtle().getY(), DELTA);
        assertEquals(5, Model.getTurtle().getX(), DELTA);
    }

    @Test
    public void testNested () {
        Model.initModel();
        Model.parseInput("to rookMove [ :step ] [ fd :step fd :step ]");
        Model.parseInput("to secondMove [ :step :rotate ] [ rookMove :step rt :rotate rookMove :step ]");
        // TODO: if variable name same as before, will call original value. If new name, will use
        // original :step value when calling rookMove
        Model.parseInput("rookMove 5");
        assertEquals(10, Model.getTurtle().getY(), DELTA);
        Model.parseInput("secondMove 3 90");
        assertEquals(16, Model.getTurtle().getY(), DELTA);
        assertEquals(6, Model.getTurtle().getX(), DELTA);
    }

    @Test
    public void testNoParams () {
        Model.initModel();
        Model.parseInput("to square [  ] [ fd 10 rt 90 fd 10 rt 90 fd 10 rt 90 fd 10 rt 90 ]");
        Model.parseInput("square");
        assertEquals(0, Model.getTurtle().getX(), DELTA);
        assertEquals(0, Model.getTurtle().getY(), DELTA);
        assertEquals(90, Model.getTurtle().getAngle(), DELTA);
    }

    @Test
    public void testCommandCache () {
        HashMap<String, String> myCommandCache;
        Model.initModel();
        Model.parseInput("to simpleMove [ :step ] [ fd :step ]");
        Model.parseInput("to secondMove [ :hello :hi ] [ rt :hello ]");
        myCommandCache = (HashMap<String, String>) Model.getAllCommands();

        for (String myKey : myCommandCache.keySet()) {
            System.out.println("Key: " + myKey);
            System.out.println("Value: " + myCommandCache.get(myKey));
        }
    }

    @Test
    public void testCallImmediately () {
        Model.initModel();
        Model.parseInput("to simpleMove [ :step ] [ fd :step ] simpleMove 2");
        assertEquals(2, Model.getTurtle().getY(), DELTA);
    }

}
