package view.inputPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import model.Model;
import view.Controller;
import view.MasterSubject;


public class InputController extends Controller {
    private Textbox myTextbox;

    public InputController (MasterSubject subject, Model model, Textbox textbox) {
        super(subject, model);
        myTextbox = textbox;

    }

    public void executeCommand () {
        String input = myTextbox.getInput();
        if (input.trim().equals("")) { return; }
        String inputError = myCurrentModel.parseInput(input);
        myTextbox.clear();
        mySubject.notifyObservers(inputError, "");
    }

    protected MouseListener addExecuteListener () {
        return new MouseListener() {

            @Override
            public void mouseClicked (MouseEvent e) {
                executeCommand();
            }

            @Override
            public void mousePressed (MouseEvent e) {

            }

            @Override
            public void mouseReleased (MouseEvent e) {

            }

            @Override
            public void mouseEntered (MouseEvent e) {

            }

            @Override
            public void mouseExited (MouseEvent e) {

            }

        };
    }

    protected MouseListener addUndoListener () {
        return new MouseListener() {

            @Override
            public void mouseClicked (MouseEvent e) {
                myCurrentModel.undo();
            }

            @Override
            public void mousePressed (MouseEvent e) {

            }

            @Override
            public void mouseReleased (MouseEvent e) {

            }

            @Override
            public void mouseEntered (MouseEvent e) {

            }

            @Override
            public void mouseExited (MouseEvent e) {

            }

        };
    }

    protected MouseListener addRedoListener () {
        return new MouseListener() {

            @Override
            public void mouseClicked (MouseEvent e) {
                myCurrentModel.redo();
            }

            @Override
            public void mousePressed (MouseEvent e) {

            }

            @Override
            public void mouseReleased (MouseEvent e) {

            }

            @Override
            public void mouseEntered (MouseEvent e) {

            }

            @Override
            public void mouseExited (MouseEvent e) {

            }

        };
    }
}
