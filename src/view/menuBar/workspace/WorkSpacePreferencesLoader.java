package view.menuBar.workspace;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import view.Constants;
import view.ViewController;


/**
 * Class that creates popup to select a workspace preference file to read in
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 * 
 */
public class WorkSpacePreferencesLoader extends AbstractAction {

    private ViewController myController;

    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir"));

    protected WorkSpacePreferencesLoader (ViewController controller) {
        super(Constants.LOAD_WORKSPACE_PREFERENCES);
        myController = controller;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        int loadObject = INPUT_CHOOSER.showOpenDialog(null);
        if (loadObject == JFileChooser.APPROVE_OPTION) {
            WorkSpacePreferenceParser p = new WorkSpacePreferenceParser(myController);
            try {
                p.loadPreferences(INPUT_CHOOSER.getSelectedFile());
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

}
