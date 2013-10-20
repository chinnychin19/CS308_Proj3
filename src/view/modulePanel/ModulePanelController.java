package view.modulePanel;

import model.Model;
import view.Controller;
import view.MasterSubject;
import view.inputPanel.Textbox;


public class ModulePanelController extends Controller {
    private Textbox myTextbox;

    public ModulePanelController (MasterSubject subject, Model model, Textbox textbox) {
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
