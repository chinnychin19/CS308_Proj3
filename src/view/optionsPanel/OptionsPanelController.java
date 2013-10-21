package view.optionsPanel;

import model.Model;
import view.Controller;
import view.MasterSubject;
import view.display.Canvas;


public class OptionsPanelController extends Controller {
    private Canvas myCanvas;

    public OptionsPanelController (MasterSubject subject, Model model, Canvas canvas) {
        super(subject, model);
        myCanvas = canvas;

    }

    protected void setBGColor (int colorIndex) {
        myCurrentModel.setBGColor(colorIndex);
        mySubject.notifyObservers("");
    }

    public void setGrid (boolean b) {
        myCanvas.setGridStatus(b);
        mySubject.notifyObservers("");

    }

    public void setTurtleStatus (boolean b) {
        myCanvas.setTurtleStatus(b);
        mySubject.notifyObservers("");

    }

}
