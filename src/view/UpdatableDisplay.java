package view;

import model.Model;


/**Allows for objects to receive 
 * a signal that they need to update their display to the user.
 * Usually these components need to update after a user action
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 */
public interface UpdatableDisplay {
    /**Method called to signify that an Updatable should update its display to user
     * @param error a way to pass errors that need to be displayed
     */
    public void updateDisplay (String error);

    /**Method to change the model an Updatable is using.
     * Updatables may use the model to gather information. Therefore, if a workspace
     * is changed, a model in an updatable needs to be swapped.
     * @param model new Model to be used
     */
    public void changeModel (Model model);
}
