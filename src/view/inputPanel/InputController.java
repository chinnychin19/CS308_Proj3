package view.inputPanel;

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

    /**
     * Method to execute textbox commands. Sends
     * typed input to Model
     */
    protected void executeCommand () {
        String input = myTextbox.getText();
        if (input.trim().equals("")) { return; }
        String inputError = myCurrentModel.parseInput(input);
        myTextbox.setText("");
        mySubject.notifyObservers(inputError);
    }

    /**
     * Sends undo command to Model
     */
    protected void undo () {
        String undoError = myCurrentModel.undo();
        mySubject.notifyObservers(undoError);
    }

    /**
     * Sends redo command to model
     */
    protected void redo () {
        String redoError = myCurrentModel.redo();
        mySubject.notifyObservers(redoError);
    }

}
