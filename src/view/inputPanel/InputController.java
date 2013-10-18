package view.inputPanel;

import javax.swing.JButton;
import model.Model;
import view.Controller;
import view.Subject;


public class InputController extends Controller {

    public InputController (Subject subject, Model model ) {
        super(subject, model);
        
    }

    public void executeCommand (String input) {
        String inputError = myCurrentModel.parseInput(input);
        mySubject.notifyObservers(inputError, "");
    }

}
