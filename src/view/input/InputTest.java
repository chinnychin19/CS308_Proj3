package view.input;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import dataType.DataType;


public class InputTest {
    Textbox textbox;
    RunButton runButton;

    @Before
    public void setUp () throws Exception {
        textbox = new Textbox(50);
        runButton = new RunButton("RUN",textbox);
        
    }

    @After
    public void tearDown () throws Exception {
    }

    @Test
    public void testTextBox () {
        assertTrue(textbox.getInput().equals(""));
        textbox.addInput("test");
        assertTrue(textbox.getInput().equals("test "));
        textbox.addInput("more input");
        assertEquals("test more input ", textbox.getInput());
        textbox.clear();
        assertEquals(textbox.getInput(), "");
        textbox.addInput("test");
        assertEquals("test ", textbox.getInput());
    }
    @Test
    public void testRunButton () {
        textbox.addInput("test");
        assertEquals("test ",runButton.sendUserInput());
        assertEquals("",textbox.getInput());
    }

}
