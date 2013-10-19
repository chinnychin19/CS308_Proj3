package view;

import model.Model;


public interface Subject {
    public void notifyObservers ();

    public void changeCurrentModel (Model model);
}
