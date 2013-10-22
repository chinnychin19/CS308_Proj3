package view.optionsPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import jgame.JGColor;
import view.Constants;
import view.View;
import view.display.Canvas;
import view.inputPanel.InputController;


public class BackgroundColorChooser extends JButton {
    private OptionsPanelController myController;

    public BackgroundColorChooser (final OptionsPanelController controller) {
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

    private int createBackgroundSelector () {
        String[] possibilities = getIndexOptions();
        String choice = (String) JOptionPane.showInputDialog(
                                                             null,                                                             
                                                             "Choose a color index",
                                                             "Background color chooser",
                                                             JOptionPane.PLAIN_MESSAGE,
                                                             null,
                                                             possibilities,
                                                             "");
        return Integer.parseInt(choice);
    }

    private String[] getIndexOptions () {
        int size = myController.getNumberOfColors();
        String[] options = new String[size];

        for (int i = 0; i < size; i++) {
            options[i] = Integer.toString(i);
        }

        return options;
    }
}
