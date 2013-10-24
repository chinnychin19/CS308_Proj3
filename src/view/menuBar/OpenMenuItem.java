package view.menuBar;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import view.Constants;
import view.ViewController;


/**
 * Menu specific to openning a file
 * 
 * @author Susan Zhang
 * @author Lalita Maraj
 * 
 */
@SuppressWarnings("serial")
class OpenMenuItem extends AbstractAction {
    private ViewController myController;

    /**
     * Constructor
     * 
     * @param controller Controller used to communicate with Model
     * @param name Name of menu
     */
    protected OpenMenuItem (ViewController controller, String name) {
        super(name);
        myController = controller;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        loadFile();
    }

    /**
     * Loads a selected file from dialog box
     */
    private void loadFile () {
        int loadObject = Constants.INPUT_CHOOSER.showOpenDialog(null);
        if (loadObject == JFileChooser.APPROVE_OPTION) {
            myController.loadFile(Constants.INPUT_CHOOSER.getSelectedFile().toString());
        }
    }
}
