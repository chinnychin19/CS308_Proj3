package view.workspace;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Model;
import view.Constants;
import view.Controller;
import view.MasterSubject;


public class WorkSpacePreferencesController extends Controller {

    private List<Controller> myControllers;
    private List<MasterSubject> mySubjects;
    private List<Model> myModels = new ArrayList<Model>();
    private int myCurrentWorkSpace = 1;
    private MasterSubject mySubject;

    public WorkSpacePreferencesController (MasterSubject subject, List<Controller> controllers,
                                           List<MasterSubject> subjects,
                                           Model model) {
        super(subject, model);
        myControllers = controllers;
        myControllers.add(this);
        mySubjects = subjects;
        myModels.add(model);
        mySubject = subject;

    }

    /**
     * @return the number of currently defined Workspaces which
     *         is determined by the number of Models we have defined
     */
    protected int getNumberOfWorkspaces () {

        return myModels.size();
    }

    private Model getCurrentModel () {
        return myModels.get(myCurrentWorkSpace-1);
    }

    /**
     * Update the controllers of the new Model
     * 
     * @param modelIndex
     */
    private void changeControllerModel (int modelIndex) {
        for (Controller controller : myControllers) {
            controller.changeCurrentModel(myModels.get(modelIndex));
        }

    }

    /**
     * Update the subjects of the new Model
     * 
     * @param masterIndex
     */
    private void changeSubjectModel (Integer masterIndex) {
        for (MasterSubject subject : mySubjects) {
            subject.changeCurrentModel(myModels.get(masterIndex));
            subject.notifyObservers("");
        }
    }

    /**
     * Based on choice GUI sends, a workspace is either created or the view is
     * switched to an existing workspace
     * 
     * @param choice - String from view indicating user's workspace option
     */
    protected void setWorkSpace (String choice) {
        if ((choice == null) || (choice.length() <= 0)) {

        }
        else if (!choice.equals(Constants.CREATE_NEW_WORK_SPACE_OPTION)) {
            switchWorkSpace(choice);
        }
        else if (choice.equals(Constants.CREATE_NEW_WORK_SPACE_OPTION)) {
            makeNewWorkSpace();

        }

    }

    /**
     * Switches from workspace to another existing workspace
     * 
     * @param choice - String from view indicating user's workspace option
     */
    private void switchWorkSpace (String choice) {

        Integer choiceVal = Integer.parseInt(choice);

        notifyNewModel(choiceVal - 1);

        String message = Constants.CHANGED_WORKSPACE_MESSAGE + choiceVal;
        displayMessage(message);
        myCurrentWorkSpace = choiceVal;
    }

    /**
     * makes and switches to a new workspace
     */
    private void makeNewWorkSpace () {
        myModels.add(new Model());
        notifyNewModel(myModels.size() - 1);
        String message =
                Constants.NEW_WORKSPACE_MESSAGE +
                        myModels.size();
        displayMessage(message);
        myCurrentWorkSpace = myModels.size();
    }

    /**
     * Notifies all Controllers and Subjects of new Model
     * being used
     * 
     * @param modelIndex - index of the model in current workspace
     */
    private void notifyNewModel (int modelIndex) {
        changeControllerModel(modelIndex);
        changeSubjectModel(modelIndex);
    }

    /**
     * Displays message
     * 
     * @param message - String indicating message
     */
    private void displayMessage (String message) {

        JOptionPane.showMessageDialog(null,
                                      message);
    }

    /**
     * @return the number of workspaces currently defined.
     */
    protected int getCurrentWorkSpace () {
        return myCurrentWorkSpace;
    }

    protected void parseWorkSpacePreferences (String filenmae) {
        // Talk the to the model
        mySubject.notifyObservers(""); // can pass an error string to notifyObsers
    }

    protected void saveWorkSpacePreferences (String filenmae) {
        // Talk the to the model
        mySubject.notifyObservers(""); // can pass an error string to notifyObsers
    }
    
    protected void setBGColor(int i){
        getCurrentModel().setBGColor(i);
        mySubject.notifyObservers("");
    }

    protected void setPenColor(int i){
        getCurrentModel().setPenColor(i);
        mySubject.notifyObservers("");
    }
    
    protected void readFile(String fileName){
        getCurrentModel().readLibrary(fileName);
        mySubject.notifyObservers("");
    }
    
    protected void changeImage(int i){
        getCurrentModel().setShape(i);
        mySubject.notifyObservers("");
    }
    
//    protected int getBGColor(int i){
//        //return getCurrentModel().getBGColor();
//    }
}
