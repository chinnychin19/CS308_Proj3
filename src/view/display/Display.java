package view.display;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Display extends JPanel {

    public Display (int width, int height) {
        super();
        this.setPreferredSize(new Dimension(width, height));
        this.add(new JLabel("I have a number between 1 and 1000."));
    }
}
