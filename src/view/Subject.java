package view;

import model.Model;


public interface Subject {
    public void notifyObservers (String error);

    public void changeCurrentModel (Model model);
}
