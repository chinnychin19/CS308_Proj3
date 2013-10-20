package view.modulePanel;

import javax.swing.JTextArea;
import model.Model;
import view.Controller;
import view.MasterSubject;


public class ModulePanelController extends Controller {
    private JTextArea myTextbox;

    public ModulePanelController (MasterSubject subject, Model model, JTextArea textbox) {
        super(subject, model);
        myTextbox = textbox;
    }

    protected String updateVariable (String key, String value) {

        String updateVariable = myCurrentModel.putVariable(key, value);
        mySubject.notifyObservers("");
        return updateVariable;

    }

    protected void populateTextBox (String clickedContent) {
        myTextbox.setText(clickedContent);
    }

}
