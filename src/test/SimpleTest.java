package test;

import static org.junit.Assert.*;
import model.Model;
import model.Turtle;
import org.junit.Test;


public class SimpleTest {

    @Test
    public void test () {
        Model myModel = new Model();
        Turtle testTurtle = new Turtle(myModel);

    }

}
