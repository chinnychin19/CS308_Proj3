package view.workspace;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import view.Constants;


@SuppressWarnings("serial")
public class WorkSpacePreferences extends JMenu {

    WorkSpacePreferencesController myController;

    /**
     * A menu that provides options for workspace preferences
     * 
     * @param controller
     */
    public WorkSpacePreferences (WorkSpacePreferencesController controller) {
        super(Constants.WORKSPACE_PREFERENCES_NAME);
        myController = controller;

        add(new AbstractAction(Constants.CHANGE_WORKSPACE_NAME) {
            @Override
            public void actionPerformed (ActionEvent e) {
                selectWorkSpace();
            }
        });

    }

    /**
     * Method that is called when a user wants to select a workspace
     */
    private void selectWorkSpace () {

        String choice = createAndDisplaySelector();

        myController.setWorkSpace(choice);

    }

    /**
     * Creates the inputDialog box based on the number of workspaces currently defined
     * 
     * @return user's choice
     */
    private String createAndDisplaySelector () {
        String[] possibilities = createWorkSpaceOptions();
        String message =
                Constants.WORK_SPACE_MESSAGE +
                        myController.getCurrentWorkSpace();
        String choice = (String) JOptionPane.showInputDialog(
                                                             null,
                                                             message,
                                                             Constants.WORK_SPACE_TITLE,
                                                             JOptionPane.PLAIN_MESSAGE,
                                                             null,
                                                             possibilities,
                                                             "1");
        return choice;
    }

    /**
     * Creates the array that is used to create the input dialog.
     * This array changes depending on how many workspaces are defined
     * 
     * @return
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
