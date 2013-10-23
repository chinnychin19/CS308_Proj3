package view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import view.display.Canvas;
import model.Model;

/**
 * Class in charge of communication between the View and Model
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 *
 */
public class ViewController {
    private JTextArea myTextbox;
    private Model myCurrentModel;

    private Canvas myCanvas;
    private int myCurrentWorkSpace = 1;
    private List<Model> myModels = new ArrayList<Model>();
    private View myView;

    public ViewController (
                           Model model,
                           JTextArea textbox,
                           Canvas canvas,
                           View view) {
        myView = view;
        myTextbox = textbox;

        myCurrentModel = model;
        myCanvas = canvas;

        // mySubjects = subjects;
        myModels.add(model);

    }

    public void executeCommand () {
        String input = myTextbox.getText();
        if (input.trim().equals("")) { return; }
        String inputError = myCurrentModel.parseInput(input);
        myTextbox.setText("");
        myView.notifyUpdatables(inputError);
    }

    /**
     * Sends undo command to Model
     */
    public void undo () {
        String undoError = myCurrentModel.undo();
        myView.notifyUpdatables(undoError);
    }

    /**
     * Sends redo command to model
     */
    public void redo () {
        String redoError = myCurrentModel.redo();
        myView.notifyUpdatables(redoError);
    }

    public String updateVariable (String key, String value) {

        String updateVariable = myCurrentModel.putVariable(key, value);
        myView.notifyUpdatables(updateVariable);
        return updateVariable;

    }

    public void populateTextBox (String clickedContent) {
        myTextbox.setText(clickedContent);
    }

    public void setBGColor (int colorIndex) {
        myCurrentModel.setBGColor(colorIndex);
        myView.notifyUpdatables("");
    }

    public void setGrid (boolean b) {
        myCanvas.setGridStatus(b);
        myView.notifyUpdatables("");

    }

    public void setTurtleStatus (boolean b) {
        myCanvas.setTurtleStatus(b);
        myView.notifyUpdatables("");

    }

    public int getNumberOfWorkspaces () {

        return myModels.size();
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
        myView.notifyUpdatables("");

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
        changeCurrentModel(modelIndex);
        changeSubjectModel(modelIndex);
    }

    private void changeSubjectModel (int modelIndex) {
        myView.changeCurrentModel(myModels.get(modelIndex));

    }

    private void changeCurrentModel (int modelIndex) {
        myCurrentModel = myModels.get(modelIndex);

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
        myView.notifyUpdatables(""); // can pass an error string to notifyObsers
    }

    protected void saveWorkSpacePreferences (String filenmae) {
        // Talk the to the model
        myView.notifyUpdatables(""); // can pass an error string to notifyObsers
    }

    public void changeLanguage (String myLanguageValue) {
        myCurrentModel.setLanguage(myLanguageValue);

    }

    public Collection<String> getLanguages () {
        return myCurrentModel.getAvailableLanguages();
    }

    public void loadFile (String fileName) {
        myCurrentModel.readLibrary(fileName);
        myView.notifyUpdatables("");
    }

    public void saveFile (String fileName) {
        myCurrentModel.saveLibrary(fileName);
    }

    public void setPenColor (int colorIndex) {
        
        myCurrentModel.setPenColor(colorIndex);
        myView.notifyUpdatables("");
        
    }

    public int getNumberOfColors () {
        return myCurrentModel.getAvailableColors().size();
    }

    public void setHighlights (boolean b) {
        myCanvas.setHighlights(b);
        myView.notifyUpdatables("");
    }

    public void changeImage (int shapeIndex) {
        myCurrentModel.setShape(shapeIndex);
        myView.notifyUpdatables("");
    }
    
    public void onClick(int x, int y){
        myCurrentModel.mouseClicked(x, y);
        myView.notifyUpdatables("");
    }
    
    public void onMove(int x, int y){
        myCurrentModel.mouseMoved(x, y);
        myView.notifyUpdatables("");
    }
    
    public void onKey(int k){
        myCurrentModel.keyPressed(k);
        myView.notifyUpdatables("");
    }
    
    public String getShape(){
        return myCurrentModel.getShape();
    }
    
    public int getBackgroundIndex(){
        return myCurrentModel.getBGColorIndex ();
    }
    
    public int getPenIndex(){
        return myCurrentModel.getPenColorIndex();
    }
}
