package view.menuBar.workspace;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import view.Constants;
import view.ViewController;


/**
 * Class that allows workspace preferences (background color, pen color, and image index)
 * to be stored in a text file
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 * 
 */
public class WorkSpacePreferencesSaver extends AbstractAction {
    private ViewController myController;
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir"));

    /**
     * Constructor for WorkSpacePreferenceSaver class
     * 
     * @param controller Controller used to send pen index selection to Model
     */
    protected WorkSpacePreferencesSaver (ViewController controller) {
        super(Constants.SAVE_WORKSPACE_PREFERENCES);
        myController = controller;
    }

    @Override
    public void actionPerformed (ActionEvent e) {

        int result = INPUT_CHOOSER.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = INPUT_CHOOSER.getSelectedFile();
            System.out.println(file);

            try {
                PrintStream out = new PrintStream(file);
                out.println("preferences");
                out.println("background " + myController.getBackgroundIndex());
                out.println("penIndex " + myController.getPenIndex());
                out.println("turtleImage " + myController.getShapeIndex());
                out.close();
            }
            catch (FileNotFoundException e1) {

                e1.printStackTrace();
            }

        }

    }

}
