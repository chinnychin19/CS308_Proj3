package view.input;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class InputPanel extends JPanel {

    public InputPanel (Textbox textbox, RunButton runbutton) {
        super();
        this.setLayout(new GridLayout(1, 1));
        this.add(new JScrollPane(textbox));
        this.add(runbutton);

    }

}
