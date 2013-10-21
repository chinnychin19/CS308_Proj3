package view.optionsPanel;

import model.Model;
import view.Controller;
import view.MasterSubject;


public class OptionsPanelController extends Controller {

    public OptionsPanelController (MasterSubject subject, Model model) {
        super(subject, model);

    }

    protected void setBGColor (int colorIndex) {
        myCurrentModel.setBGColor(colorIndex);
        mySubject.notifyObservers("");
    }

    public void setGrid (boolean b) {
        mySubject.notifyObservers("");

    }

    public void setStatus (boolean b) {
        mySubject.notifyObservers("");

    }
}
