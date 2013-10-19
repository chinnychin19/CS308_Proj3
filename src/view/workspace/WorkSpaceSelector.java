package view.workspace;

import java.util.List;
import javax.swing.JOptionPane;
import model.Model;
import view.Controller;
import view.Subject;


public class WorkSpaceSelector {

    List<Controller> myControllers;
    List<Subject> mySubjects;
    List<Model> myModels;

    public WorkSpaceSelector (List<Controller> controllers,
                              List<Subject> subjects,
                              List<Model> models) {
        myControllers = controllers;
        mySubjects = subjects;
        myModels = models;
    }

    public String NewWorkSpace () {
        int size = myModels.size() + 1;
        Object[] possibilities = new Object[size];
        int i;
        for (i = 0; i < size - 1; i++) {
            possibilities[i] = Integer.toString(i + 1);
        }
        possibilities[i] = "Create New WorkSpace";
        String choice = (String) JOptionPane.showInputDialog(
                                                             null,
                                                             "Choose a Work Space",
                                                             "Work Space",
                                                             JOptionPane.PLAIN_MESSAGE,
                                                             null,
                                                             possibilities,
                                                             "1");

        if ((choice != null) && (choice.length() > 0) && !choice.equals("Create New WorkSpace")) {
            Integer choiceVal = Integer.parseInt(choice);
            for (Controller controller : myControllers) {
                controller.changeCurrentModel(myModels.get(choiceVal - 1));
            }
            for (Subject subject : mySubjects) {
                subject.changeCurrentModel(myModels.get(choiceVal - 1));
                subject.notifyObservers("", "");
            }
            String message = "You are now in work space " + choiceVal;
            JOptionPane.showMessageDialog(null,
                                          message);

            return choice;
        }

        else {
            myModels.add(new Model());
            for (Controller controller : myControllers) {
                controller.changeCurrentModel(myModels.get(myModels.size() - 1));
            }
            for (Subject subject : mySubjects) {
                subject.changeCurrentModel(myModels.get(myModels.size() - 1));
                subject.notifyObservers("", "");
            }
            String message =
                    "A new work space has been created. You are now in work space " +
                            myModels.size();
            JOptionPane.showMessageDialog(null,
                                          message);

            return choice;
        }

    }

}
