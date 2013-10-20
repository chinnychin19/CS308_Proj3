package view.workspace;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import view.Constants;
import view.Controller;
import view.MasterSubject;
import model.Model;


public class WorkSpaceSelector extends JMenu implements WorkSpaceSelectorObserver {

    WorkSpaceSelectorController myController;

    public WorkSpaceSelector (WorkSpaceSelectorController controller) {
        super("Workspace Preferences");
        myController = controller;
        add(new AbstractAction("Change Workspace") {
            @Override
            public void actionPerformed (ActionEvent e) {
                selectWorkSpace();
            }
        });

    }

    public String toString () {
        return "selectr";
    }

    private String selectWorkSpace () {

        String choice = createAndDisplaySelector();
        return choice;
        // return changeWorkSpace(choice);

    }

    private String createAndDisplaySelector () {
        String[] possibilities = createWorkSpaceOptions();
        String choice = (String) JOptionPane.showInputDialog(
                                                             null,
                                                             Constants.WORK_SPACE_MESSAGE,
                                                             Constants.WORK_SPACE_TITLE,
                                                             JOptionPane.PLAIN_MESSAGE,
                                                             null,
                                                             possibilities,
                                                             "1");
        return choice;
    }

    // private String changeWorkSpace (String choice) {
    // if (!choice.equals(Constants.CREATE_NEW_WORK_SPACE_OPTION)) {
    // switchWorkSpace(choice);
    // }
    // else {
    // makeNewWorkSpace();
    //
    // }
    // return choice;
    // }

    // private void switchWorkSpace (String choice) {
    //
    // Integer choiceVal = Integer.parseInt(choice);
    // changeControllerModel(choiceVal - 1);
    // changeSubjectModel(choiceVal - 1);
    //
    // String message = Constants.CHANGED_WORKSPACE_MESSAGE + choiceVal;
    // displayMessage(message);
    //
    // }

    // private void makeNewWorkSpace () {
    // myModels.add(new Model());
    // changeControllerModel(myModels.size() - 1);
    // changeSubjectModel(myModels.size() - 1);
    // String message =
    // Constants.NEW_WORKSPACE_MESSAGE +
    // myModels.size();
    // displayMessage(message);
    // }
    //
    // private void displayMessage (String message) {
    //
    // JOptionPane.showMessageDialog(null,
    // message);
    // }
    //
    // private void changeControllerModel (int modelIndex) {
    // for (Controller controller : myControllers) {
    // controller.changeCurrentModel(myModels.get(modelIndex));
    // }
    // }

    private String[] createWorkSpaceOptions () {
        int size = myController.getModelsSize();
        String[] possibilities = new String[size];
        int i;
        for (i = 0; i < size - 1; i++) {
            possibilities[i] = Integer.toString(i + 1);
        }
        possibilities[i] = Constants.CREATE_NEW_WORK_SPACE_OPTION;
        return possibilities;

    }

    //
    // private void changeSubjectModel (Integer masterIndex) {
    // for (MasterSubject subject : mySubjects) {
    // subject.changeCurrentModel(myModels.get(masterIndex));
    // subject.notifyObservers("");
    // }
    // }

    @Override
    public void update (int numberModels, boolean displayStatus) {
        // TODO Auto-generated method stub

    }

}
