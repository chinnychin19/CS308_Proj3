package view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import view.display.Canvas;
import model.Model;


/**
 * Class that facilitates communication between the View and Model
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

    /**
     * Constructor for ViewController class
     * 
     * @param model current Model
     * @param textbox textbox for input
     * @param canvas canvas
     * @param view view
     */
    public ViewController (
                           Model model,
                           JTextArea textbox,
                           Canvas canvas,
                           View view) {
        myView = view;
        myTextbox = textbox;

        myCurrentModel = model;
        myCanvas = canvas;

        myModels.add(model);

    }

    /**
     * Sends String from textbox to Model to be parsed and updates relevant classes
     */
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

    /**
     * Updates value of variable
     * 
     * @param key
     * @param value
     * @return
     */
    public String updateVariable (String key, String value) {
        String updateVariable = myCurrentModel.putVariable(key, value);
        myView.notifyUpdatables(updateVariable);
        return updateVariable;

    }

    /**
     * Adds clicked input display to textbox
     * 
     * @param clickedContent input display String
     */
    public void populateTextBox (String clickedContent) {
        myTextbox.setText(clickedContent);
    }
 
    /**
     * Sets background color to color at index stored in Model
     * 
     * @param colorIndex index of color
     */
    public void setBGColor (int colorIndex) {
        myCurrentModel.setBGColor(colorIndex);
        myView.notifyUpdatables("");
    }

    /**
     * Sets status of grid in Canvas
     * 
     * @param b status of grid
     */
    public void setGrid (boolean b) {
        myCanvas.setGridStatus(b);
        myView.notifyUpdatables("");
    }

    /**
     * Sets status of turtle status displaying
     * 
     * @param b status of turtle status
     */
    public void setTurtleStatus (boolean b) {
        myCanvas.setTurtleStatus(b);
        myView.notifyUpdatables("");
    }

    /**
     * Returns number of workspaces
     * 
     * @return number of current workspaces
     */
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
            return;
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
     * Returns the current workspace
     * 
     * @return current workspace
     */
    public int getCurrentWorkSpace () {
        return myCurrentWorkSpace;
    }

    /**
     * Changes the language of the model
     * 
     * @param myLanguageValue new language to be set
     */
    public void changeLanguage (String myLanguageValue) {
        myCurrentModel.setLanguage(myLanguageValue);
    }

    /**
     * Gets Collection of available languages from Model
     * 
     * @return Collection of currrently available languages
     */
    public Collection<String> getLanguages () {
        return myCurrentModel.getAvailableLanguages();
    }

    /**
     * Loads a variable/command file
     * 
     * @param fileName name of file
     */
    public void loadFile (String fileName) {
        myCurrentModel.readLibrary(fileName);
        myView.notifyUpdatables("");
    }

    /**
     * Saves file of variables/commands
     * 
     * @param fileName name of file
     */
    public void saveFile (String fileName) {
        myCurrentModel.saveLibrary(fileName);
    }

    /**
     * Sets pen color to new index in Model
     * 
     * @param colorIndex new pen color index
     */
    public void setPenColor (int colorIndex) {
        myCurrentModel.setPenColor(colorIndex);
        myView.notifyUpdatables("");

    }

    /**
     * Gets number of available colors from Model
     * 
     * @return
     */
    public int getNumberOfColors () {
        return myCurrentModel.getAvailableColors().size();
    }

    /**
     * Sets status of highlighting in Canvas
     * 
     * @param status whether active turtles should be highlighted in canvas
     */
    public void setHighlights (boolean status) {
        myCanvas.setHighlights(status);
        myView.notifyUpdatables("");
    }

    /**
     * Changes image index of turtle
     * 
     * @param shapeIndex new index of image
     */
    public void changeImage (int shapeIndex) {
        myCurrentModel.setShape(shapeIndex);
        myView.notifyUpdatables("");
    }

    /**
     * Runs ONCLICK command if defined in Model
     * 
     * @param x x position
     * @param y y position
     */
    public void onClick (int x, int y) {
        myCurrentModel.mouseClicked(x, y);
        myView.notifyUpdatables("");
    }

    /**
     * Runs ONMOVE command if defined in Model
     * 
     * @param x x position
     * @param y y position
     */
    public void onMove (int x, int y) {
        myCurrentModel.mouseMoved(x, y);
        myView.notifyUpdatables("");
    }

    /**
     * Runs ONKEY command if defined in Model
     * 
     * @param k keyCode of pressed key
     */
    public void onKey (int k) {
        myCurrentModel.keyPressed(k);
        myView.notifyUpdatables("");
    }

    /**
     * Returns current shape of turtle
     * 
     * @return name of current shape of turtle
     */
    public String getShape () {
        return myCurrentModel.getShape();
    }

    /**
     * Returns current shape index of turtle
     * 
     * @return shape index of turtle
     */
    public int getShapeIndex () {
        return myCurrentModel.getShapeIndex();
    }

    /**
     * Returns index of background color
     * 
     * @return background color index
     */
    public int getBackgroundIndex () {
        return myCurrentModel.getBGColorIndex();
    }

    /**
     * Returns index of pen color
     * 
     * @return pen color index
     */
    public int getPenIndex () {
        return myCurrentModel.getPenColorIndex();
    }
}
