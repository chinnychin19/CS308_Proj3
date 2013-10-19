package view.modulePanel;

import model.Model;
import view.Controller;
import view.MasterSubject;


public class ModulePanelController extends Controller {
    public ModulePanelController (MasterSubject subject, Model model) {
        super(subject, model);
        // TODO Auto-generated constructor stub
    }

    protected String updateVariable (String key, String value) {

        String updateVariable = myCurrentModel.putVariable(key, value);
        mySubject.notifyObservers("", updateVariable);
        return updateVariable;

    }

}
