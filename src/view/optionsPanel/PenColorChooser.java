package view.optionsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import view.Constants;
import view.ViewController;


/**
 * Class for button that enables changing of pen color based on available color indexes
 * stored in Model class
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 * 
 */
public class PenColorChooser extends JButton {
    ViewController myController;

    /**
     * Constructor for PenColorChooser class
     * 
     * @param controller Controller used to send pen index selection to Model
     */
    public PenColorChooser (final ViewController controller) {
        super("Change Pen Color");
        myController = controller;

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                int i = createPenSelector();
                myController.setPenColor(i);
            }

        });
    }

    /**
     * Method that returns an integer based on user's selection from InputDialog dropdown
     * 
     * @return new pen index
     */
    private int createPenSelector () {
        String[] possibilities = getIndexOptions();
        String choice =
                (String) JOptionPane.showInputDialog(
                                                     null,
                                                     Constants.CHOOSE_COLOR_INDEX +
                                                             myController.getBackgroundIndex(),
                                                     Constants.PEN_CHOOSER_TITLE,
                                                     JOptionPane.PLAIN_MESSAGE,
                                                     null,
                                                     possibilities,
                                                     "");
        if (choice == null) { return myController.getPenIndex(); }
        return Integer.parseInt(choice);
    }

    /**
     * Method that returns a string array of options based on Model's array of available colors
     * 
     * @return
     */
    private String[] getIndexOptions () {
        int size = myController.getNumberOfColors();
        String[] options = new String[size];

        for (int i = 0; i < size; i++) {
            options[i] = Integer.toString(i);
        }

        return options;
    }

}
