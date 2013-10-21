package view.workspace;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import view.Constants;

public class WorkSpacePreferencesLoader extends AbstractAction {
    private WorkSpacePreferencesController myController;

    protected  WorkSpacePreferencesLoader(WorkSpacePreferencesController controller){
        super(Constants.LOAD_WORKSPACE_PREFERENCES);
        myController = controller;
    }
    @Override
    public void actionPerformed (ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

}
