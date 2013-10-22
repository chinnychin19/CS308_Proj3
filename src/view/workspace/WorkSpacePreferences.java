package view.workspace;

import javax.swing.JMenu;
import view.Constants;
import view.ViewController;


@SuppressWarnings("serial")
public class WorkSpacePreferences extends JMenu {

    ViewController myController;

    /**
     * A menu that provides options for workspace preferences
     * 
     * @param controller
     */
    public WorkSpacePreferences (ViewController controller) {
        super(Constants.WORKSPACE_PREFERENCES_NAME);
        myController = controller;

        add(new WorkSpaceSelector(myController));
        add(new WorkSpacePreferencesLoader(myController));
        add(new WorkSpacePreferencesSaver(myController));

    }

}
