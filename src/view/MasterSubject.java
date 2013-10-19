package view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import view.modulePanel.ModuleData;
import view.modulePanel.ModuleSubject;
import model.Model;
import model.Path;


public class MasterSubject {

    List<Subject> subjects = new ArrayList<Subject>();

    Model myCurrentModel;
    View myView;

    public MasterSubject (Model model, View view) {
        myCurrentModel = model;
        myView = view;

    }

    public void addSubject (Subject newSubject) {
        subjects.add(newSubject);

    }

    public void notifyObservers (String error, String updateVariable) {

        for (Subject subject : subjects) {
            subject.notifyObservers();

        }

    }

    public void changeCurrentModel (Model model) {
        myCurrentModel = model;
        for (Subject subject : subjects) {
            subject.changeCurrentModel(myCurrentModel);

        }

    }

}
