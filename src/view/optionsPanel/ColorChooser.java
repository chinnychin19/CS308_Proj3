package view.optionsPanel;

import javax.swing.JButton;
import view.ViewController;


/**
 * Superclass for color changing button dialog
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 * 
 */
public class ColorChooser extends JButton {
    protected ViewController myController;

    /**
     * Constructor for ColorChooser class
     * 
     * @param title title of color chooser
     * @param controller controller
     */
    public ColorChooser (String title, ViewController controller) {
        super(title);
        myController = controller;
    }

    /**
     * Method that returns a string array of options based on Model's array of available colors
     * 
     * @return
     */
    protected String[] getIndexOptions () {
        int size = myController.getNumberOfColors();
        String[] options = new String[size];

        for (int i = 0; i < size; i++) {
            options[i] = Integer.toString(i);
        }

        return options;
    }

}
