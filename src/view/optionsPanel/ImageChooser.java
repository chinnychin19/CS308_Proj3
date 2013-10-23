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
public class ImageChooser extends JButton {
    ViewController myController;

    public ImageChooser (final ViewController controller) {
        super(Constants.CHANGE_IMAGE_BUTTON);
        myController = controller;

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                int i = createImageSelector();
                myController.changeImage(i - 1);

            }

        });
    }

    private int createImageSelector () {
        String choice =
                (String) JOptionPane.showInputDialog(
                                                     null,
                                                     Constants.CHOOSE_IMAGE +
                                                             myController.getShape(),
                                                     Constants.IMAGE_CHOOSER_TITLE,
                                                     JOptionPane.PLAIN_MESSAGE,
                                                     null,
                                                     Constants.TURTLE_OPTIONS,
                                                     "");
        if (choice == null) {
            choice = myController.getShape();
        }
        return Integer.parseInt(choice.substring(Constants.SHAPE_NAME_LENGTH - 1,
                                                 Constants.SHAPE_NAME_LENGTH));
    }

}
