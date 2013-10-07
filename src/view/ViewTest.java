package view;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import dataType.DataType;
import view.input.Textbox;

public class ViewTest {
    Textbox textbox;
    @Before
    public void setUp () throws Exception {
        textbox = new Textbox(50);
    }

    @After
    public void tearDown () throws Exception {
    }

    @Test
    public void testTextBox () {
        textbox.addInput("test");
        assertEquals(textbox.getInput(), "test");
        textbox.addInput("more input");
        assertEquals(textbox.getInput(), "test more input");
        textbox.clear();
        assertEquals(textbox.getInput(),"");
      
    }

}
