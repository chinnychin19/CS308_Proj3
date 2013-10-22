package view.optionsPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import jgame.JGColor;
import view.View;
import view.display.Canvas;


public class PenColorChooser extends JButton {
    OptionsPanelController myController;

    public PenColorChooser (final OptionsPanelController controller) {
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
    
    private int createPenSelector () {
        String[] possibilities = getIndexOptions();
        String choice = (String) JOptionPane.showInputDialog(
                                                             null,                                                             
                                                             "Choose a color index",
                                                             "Pen color chooser",
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
