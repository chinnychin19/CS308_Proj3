package view.inputPanel;

// package view.input;
//
// import static org.junit.Assert.*;
// import model.Model;
// import org.junit.Test;
//
//
// public class ModelViewInputTest {
// private final double DELTA = 0.001;
// private Textbox textbox = new Textbox(50);
// private RunButton runButton = new RunButton("RUN", textbox);
//
// @Test
// public void testSendInput () {
//
// textbox.addInput("fd 10");
// runButton.sendUserInput();
//
// textbox.addInput("fd -20");
// runButton.sendUserInput();
//
// textbox.addInput("fd 20 fd -10");
// runButton.sendUserInput();
// // myModel.parseInput("right 90 fd 10");
//
// Model.processNextInstruction();
// assertEquals("Foward positive", 10, Model.getTurtleY(), DELTA);
//
// Model.processNextInstruction();
// assertEquals("Forward negative", -10, Model.getTurtleY(), DELTA);
//
// Model.processNextInstruction();
// assertEquals("Foward positive", 10, Model.getTurtleY(), DELTA);
//
// Model.processNextInstruction();
// assertEquals("Forward negative", 0, Model.getTurtleY(), DELTA);
//
// }
//
// }
