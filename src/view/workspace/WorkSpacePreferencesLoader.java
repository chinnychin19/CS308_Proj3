package view.workspace;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import view.Constants;
import view.ViewController;


public class WorkSpacePreferencesLoader extends AbstractAction {
    private ViewController myController;

    protected WorkSpacePreferencesLoader (ViewController controller) {
        super(Constants.LOAD_WORKSPACE_PREFERENCES);
        myController = controller;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        // TODO Auto-generated method stub

    }

}
