package view.inputPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextArea;
import model.Model;
import view.Controller;
import view.MasterSubject;


public class InputController extends Controller {
    private JTextArea myTextbox;

    public InputController (MasterSubject subject, Model model, JTextArea textbox) {
        super(subject, model);
        myTextbox = textbox;

    }

    public void executeCommand () {
        String input = myTextbox.getText();
        if (input.trim().equals("")) { return; }
        String inputError = myCurrentModel.parseInput(input);
        myTextbox.setText("");
        mySubject.notifyObservers(inputError);
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
                String undoError = myCurrentModel.undo();
                mySubject.notifyObservers(undoError);
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
                String redoError = myCurrentModel.redo();
                mySubject.notifyObservers(redoError);

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
