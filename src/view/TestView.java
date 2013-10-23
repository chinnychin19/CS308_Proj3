package view;

import static org.junit.Assert.*;
import javax.swing.JTextArea;
import model.Model;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view.display.Canvas;


public class TestView {
    Model model;
    JTextArea textbox;
    Canvas canvas;
    View view;
    ViewController controller;

    @Before
    public void setUp () throws Exception {
        model = new Model();
        textbox = new JTextArea();
        canvas = new Canvas(model);
        view = new View();
        controller = new ViewController(model, textbox, canvas, view);
        canvas.addController(controller);
    }

    @After
    public void tearDown () throws Exception {
    }

    @Test
    public void testRunCommands () {
        // Test to ensure that controller relays information to model
        // and textbox is cleared after command is executed
        textbox.setText("fd 10");
        controller.executeCommand();
        assertEquals(10, model.getTurtleY(1), 0.01);
        assertEquals("", textbox.getText());

        // Background Test
        textbox.setText("setbg 1");
        controller.executeCommand();
        assertEquals(1, model.getBGColorIndex(), 0.01);
        assertEquals("", textbox.getText());
    }

    @Test
    public void testVariableUpdates () {
        textbox.setText("make :x 10");
        controller.executeCommand();

        controller.updateVariable("x", "20.0");
        assertEquals("20.0", model.getAllVariables().get("x"));
    }

    @Test
    public void testWorkSpaceChange () {
        textbox.setText("make :y 10");
        controller.executeCommand();

        controller.setWorkSpace(Constants.CREATE_NEW_WORK_SPACE_OPTION);

        textbox.setText("make :x 200");
        controller.executeCommand();

        controller.setWorkSpace("1");
        assertEquals("10.0", model.getAllVariables().get(":y"));

    }

    @Test
    public void testDefineCommands () {
        textbox.setText("to foo [ :x ] [ fd :x ]");
        controller.executeCommand();

        assertEquals("TO foo \n[ [:x] ] \n[ fd :x ]"
                     , (model.getAllCommands().get("FOO")));

    }

}
