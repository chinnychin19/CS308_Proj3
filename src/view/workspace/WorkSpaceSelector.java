package view.workspace;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import view.Constants;
import view.ViewController;


/**
 * An Abstract Action accessed from the MenuBar that Swing supports.
 * This Abstract Action provides you with an interface to change
 * the current workspace
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 * 
 */
@SuppressWarnings("serial")
class WorkSpaceSelector extends AbstractAction {
    private ViewController myController;

    /**Constructor
     * @param controller Controller used to send workspace selection to Model
     */
    protected WorkSpaceSelector (ViewController controller) {
        super(Constants.CHANGE_WORKSPACE_NAME);
        myController = controller;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        selectWorkSpace();

    }

    /**
     * Method that is called when a user wants to select a workspace
     * This method will facilitate creating the Dialog box
     * to allow a user to change their workspac
     */
    private void selectWorkSpace () {

        String choice = createAndDisplaySelector();

        myController.setWorkSpace(choice);

    }

    /**
     * Creates the inputDialog box based on the number of workspaces currently defined
     * 
     * @return user's choice as  a String
     */
    private String createAndDisplaySelector () {
        String[] possibilities = createWorkSpaceOptions();
        String title =
                Constants.WORK_SPACE_MESSAGE;

        String choice =
                (String) JOptionPane.showInputDialog(null, Constants.CHANGED_WORKSPACE_MESSAGE +
                                                           myController.getCurrentWorkSpace(),
                                                     title, JOptionPane.PLAIN_MESSAGE, null,
                                                     possibilities,
                                                     "1");
        return choice;
    }

    /**
     * Creates the array that is used to create the input dialog.
     * This array changes depending on how many workspaces are defined
     * 
     * @return the array of possible choices represented as String objects
     */
    private String[] createWorkSpaceOptions () {
        int size = myController.getNumberOfWorkspaces() + 1;
        String[] possibilities = new String[size];
        int i;
        for (i = 0; i < size - 1; i++) {
            possibilities[i] = Integer.toString(i + 1);
        }
        possibilities[i] = Constants.CREATE_NEW_WORK_SPACE_OPTION;

        return possibilities;

    }

}
