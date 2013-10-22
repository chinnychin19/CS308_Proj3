package view.optionsPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import jgame.JGColor;
import view.View;
import view.ViewController;
import view.display.Canvas;


public class BackgroundColorChooser extends JButton {

    public BackgroundColorChooser (final ViewController controller) {
        super("Change BG Color");

        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed (ActionEvent e) {
                Color newColor =
                        JColorChooser.showDialog(null, "Choose a new background color", Color.red);
                controller.setBGColor(45);
                // myCanvas.changeBackgroundColor(new JGColor(newColor.getRed(),
                // newColor.getGreen(), newColor
                // .getBlue()));
            }

        });
    }

}
