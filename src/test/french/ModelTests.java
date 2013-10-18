package test.french;

import static org.junit.Assert.*;
import model.Model;
import org.junit.Test;


public class ModelTests {

    @Test
    public void testCommandHistory () {
        Model model = new Model();
        String[] testString = { "fd 10", "fd 10", "fd 10" };
        for (int i = 0; i < testString.length; i++) {
            model.parseInput(testString[i]);
            assertEquals("String " + i, testString[i], model.getHistory().get(i));
        }
    }
}
