package view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import view.display.Canvas;
import model.Model;


public class ViewController {
    private JTextArea myTextbox;
    private Model myCurrentModel;
    private MasterSubject mySubject;
    private Canvas myCanvas;
    private int myCurrentWorkSpace = 1;
    private List<Model> myModels = new ArrayList<Model>();
    private List<Subject> mySubjects = new ArrayList<Subject>();

    public ViewController (MasterSubject subject,
                           Model model,
                           JTextArea textbox,
                           Canvas canvas,
                           List<Subject> subjects) {

        myTextbox = textbox;
        mySubject = subject;
        myCurrentModel = model;
        myCanvas = canvas;

        mySubjects = subjects;
        myModels.add(model);

    }

    public void executeCommand () {
        String input = myTextbox.getText();
        if (input.trim().equals("")) { return; }
        String inputError = myCurrentModel.parseInput(input);
        myTextbox.setText("");
        mySubject.notifyObservers(inputError);
    }

    /**
     * Sends undo command to Model
     */
    public void undo () {
        String undoError = myCurrentModel.undo();
        mySubject.notifyObservers(undoError);
    }

    /**
     * Sends redo command to model
     */
    public void redo () {
        String redoError = myCurrentModel.redo();
        mySubject.notifyObservers(redoError);
    }

    public String updateVariable (String key, String value) {

        String updateVariable = myCurrentModel.putVariable(key, value);
        mySubject.notifyObservers(updateVariable);
        return updateVariable;

    }

    public void populateTextBox (String clickedContent) {
        myTextbox.setText(clickedContent);
    }

    public void setBGColor (int colorIndex) {
        myCurrentModel.setBGColor(colorIndex);
        mySubject.notifyObservers("");
    }

    public void setGrid (boolean b) {
        myCanvas.setGridStatus(b);
        mySubject.notifyObservers("");

    }

    public void setTurtleStatus (boolean b) {
        myCanvas.setTurtleStatus(b);
        mySubject.notifyObservers("");

    }

    /**
     * @return the number of currently defined Workspaces which
     *         is determined by the number of Models we have defined
     */
    public int getNumberOfWorkspaces () {

        return myModels.size();
    }

    private Model getCurrentModel () {
        return myModels.get(myCurrentWorkSpace);
    }

    /**
     * Based on choice GUI sends, a workspace is either created or the view is
     * switched to an existing workspace
     * 
     * @param choice - String from view indicating user's workspace option
     */
    public void setWorkSpace (String choice) {
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

        String message = Constants.CHANGED_WORKSPACE_MESSAGE + choiceVal;
        displayMessage(message);
        myCurrentWorkSpace = choiceVal;
    }

    /**
     * makes and switches to a new workspace
     */
    private void makeNewWorkSpace () {
        myModels.add(new Model());

        String message =
                Constants.NEW_WORKSPACE_MESSAGE +
                        myModels.size();
        displayMessage(message);
        myCurrentWorkSpace = myModels.size();
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
    public int getCurrentWorkSpace () {
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

    public void changeLanguage (String myLanguageValue) {
        myCurrentModel.setLanguage(myLanguageValue);

    }

    public Collection<String> getLanguages () {
        return myCurrentModel.getAvailableLanguages();
    }

}
