package view.optionsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import view.Constants;
import view.ViewController;


public class ImageChooser extends JButton {
    ViewController myController;

    public ImageChooser (final ViewController controller) {
        super("Change Turtle Image");
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
        String choice = (String) JOptionPane.showInputDialog(
                                                             null,
                                                             "Choose a turtle image",
                                                             "Turtle Image Chooser",
                                                             JOptionPane.PLAIN_MESSAGE,
                                                             null,
                                                             Constants.TURTLE_OPTIONS,
                                                             "");
        return Integer.parseInt(choice.substring(6, 7));
    }

}
