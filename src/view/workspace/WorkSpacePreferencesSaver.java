package view.workspace;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import view.Constants;

public class WorkSpacePreferencesSaver extends AbstractAction {
    private WorkSpacePreferencesController myController;

    protected  WorkSpacePreferencesSaver(WorkSpacePreferencesController controller){
        super(Constants.SAVE_WORKSPACE_PREFERENCES);
        myController = controller;
    }
    @Override
    public void actionPerformed (ActionEvent e) {
        // TODO Auto-generated method stub
        
    }

}
