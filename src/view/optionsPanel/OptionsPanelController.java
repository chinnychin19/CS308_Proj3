package view.optionsPanel;

import model.Model;
import view.Controller;
import view.MasterSubject;
import view.display.Canvas;


public class OptionsPanelController extends Controller {

    public OptionsPanelController (MasterSubject subject, Model model) {
        super(subject, model);

    }

    protected void setBGColor (int colorIndex) {
        myCurrentModel.setBGColor(colorIndex);
        mySubject.notifyObservers("");
    }
}
