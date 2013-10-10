package view.input;

import static org.junit.Assert.*;
import javax.swing.JPanel;
import model.Model;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view.Constants;
import view.sidebar.SidebarPanel;
import dataType.DataType;


public class InputTest {
    Textbox textbox;
    RunButton runButton;
    SidebarPanel sidebarPanel;

    @Before
    public void setUp () throws Exception {
        textbox = new Textbox(50);
        sidebarPanel = new SidebarPanel(textbox);
        runButton = new RunButton("RUN", textbox, sidebarPanel);

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
    public void testExecute () {
        textbox.addInput("test");
        assertEquals("test ", runButton.sendUserInput());
        assertEquals("", textbox.getInput());
        textbox.addInput("          ");
        assertEquals("", runButton.sendUserInput());
        textbox.clear();

    }

    @Test
    public void testCommandHistory () {
        String[] testString = { "Command 1", "Command 2", "Command 3" };
        // empty

        for (int i = 0; i < testString.length; i++) {
            textbox.addInput(testString[i]);
            runButton.sendUserInput();
            assertEquals("String " + i, testString[i], Model.getHistory().get(i));
        }
    }

}
