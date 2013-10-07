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
        assertEquals("test", textbox.getInput());
        textbox.addInput(" more input");
        assertEquals("test more input", textbox.getInput());
        textbox.clear();
        assertEquals(textbox.getInput(), "");
        // textbox.addInput("test\n");
        // assertEquals("test\n",textbox.getInput());
    }

    public void testRunButton () {

    }

}
