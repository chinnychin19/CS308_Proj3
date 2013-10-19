package view;

import model.Model;


public class Controller {
    protected Model myCurrentModel;
    protected MasterSubject mySubject;

    public Controller (MasterSubject subject, Model model) {
        mySubject = subject;
        myCurrentModel = model;

    }

    public void changeCurrentModel (Model model) {
        myCurrentModel = model;
    }
}
