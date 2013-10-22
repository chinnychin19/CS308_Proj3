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


public class MasterSubject implements Subject {

    List<Subject> subjects = new ArrayList<Subject>();

    Model myCurrentModel;

    public MasterSubject (Model model) {
        myCurrentModel = model;

    }

    public void addSubject (Subject newSubject) {
        subjects.add(newSubject);

    }

    public void notifyObservers (String error) {

        for (Subject subject : subjects) {
            subject.notifyObservers(error);
        }

    }

    public void changeCurrentModel (Model model) {
        myCurrentModel = model;
        for (Subject subject : subjects) {
            subject.changeCurrentModel(myCurrentModel);

        }

    }

}
