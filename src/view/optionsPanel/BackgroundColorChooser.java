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
 * @author Lalita Maraj
 * @author Susan Zhang
 * 
 */
public class BackgroundColorChooser extends ColorChooser {
    private ViewController myController;

    /**
     * Class constructor for BackgroundColorChooser class
     * 
     * @param controller Controller used to send background index selection to Model
     */
    public BackgroundColorChooser (final ViewController controller) {
        super(Constants.CHANGE_BG_BUTTON, controller);
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

 
}
