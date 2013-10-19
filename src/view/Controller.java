package view;

import model.Model;


public class Controller {
    protected Model myCurrentModel;
    protected Subject mySubject;

    public Controller (Subject subject, Model model) {
        mySubject = subject;
        myCurrentModel = model;

    }

    public void changeCurrentModel (Model model) {
        myCurrentModel = model;
    }
}
