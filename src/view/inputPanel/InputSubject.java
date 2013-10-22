package view.inputPanel;

import java.util.ArrayList;
import java.util.List;
import view.Subject;
import model.Model;


public class InputSubject implements Subject {
    List<InputObserver> observers;
    Model myCurrentModel;

    public InputSubject (Model model, InputObserver inputObserver) {
        myCurrentModel = model;

        observers = new ArrayList<InputObserver>();
        addObservers(inputObserver);
    }

    private void addObservers (InputObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers (String error) {
        for (InputObserver observer : observers) {
            observer.update(canUndo(), canRedo());
        }
    }

    @Override
    public void changeCurrentModel (Model model) {
        myCurrentModel = model;

    }

    /**
     * Speaks to model to determine if a user can select undo
     * 
     * @return boolean to indicate if undo is possible
     */
    private Boolean canUndo () {
        return myCurrentModel.canUndo();
    }

    /**
     * Speaks to model to determine if a user can select redo
     * 
     * @return boolean to indicate if redo is possible
     */
    private Boolean canRedo () {
        return myCurrentModel.canRedo();

    }
}
