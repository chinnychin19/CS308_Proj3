package view.optionsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import view.Constants;
import view.ViewController;


/**
 * Class for button that enables changing of background color based on available color indexes
 * stored in Model class
 * 
 * @author susanzhang93
 * 
 */
public class BackgroundColorChooser extends JButton {
    private ViewController myController;

    /**
     * Class constructor for BackgroundColorChooser class
     * 
     * @param controller Controller used to send background index selection to Model
     */
    public BackgroundColorChooser (final ViewController controller) {
        super("Change BG Color");
        myController = controller;

        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed (ActionEvent e) {
                int i = createBackgroundSelector();
                myController.setBGColor(i);
            }

        });

    }

    /**
     * Method that returns an integer based on user's selection from InputDialog dropdown
     * 
     * @return new background index
     */
    private int createBackgroundSelector () {
        String[] possibilities = getIndexOptions();
        String choice =
                (String) JOptionPane.showInputDialog(
                                                     null,
                                                     Constants.CHOOSE_COLOR_INDEX +
                                                             myController.getBackgroundIndex(),
                                                     Constants.BG_CHOOSER_TITLE,
                                                     JOptionPane.PLAIN_MESSAGE,
                                                     null,
                                                     possibilities,
                                                     "");
        if (choice == null) { return myController.getBackgroundIndex(); }
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
