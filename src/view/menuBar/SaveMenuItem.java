package view.menuBar;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import view.Constants;
import view.ViewController;


/**
 * Menu bar specific to Saving files
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 */
@SuppressWarnings("serial")
public class SaveMenuItem extends AbstractAction {
    private ViewController myController;

    /**A menu that allows users to save currently defined environment factors
     * like variables and commands
     * @param name Name of menu
     * @param controller Controller used to communicate with View
     */
    protected SaveMenuItem (String name, ViewController controller) {
        super(name);
        myController = controller;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        saveVariableAndCommand();

    }

    /**
     * Saves a file that stores variables and command
     */
    private void saveVariableAndCommand () {
        int result = Constants.INPUT_CHOOSER.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = Constants.INPUT_CHOOSER.getSelectedFile();
            myController.saveFile(file.toString());

        }
    }

}
