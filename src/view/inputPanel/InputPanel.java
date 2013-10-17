package view.inputPanel;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


@SuppressWarnings("serial")
public class InputPanel extends JPanel {

    public InputPanel (Textbox textbox, RunButton runbutton) {
        super();
        this.setLayout(new GridLayout(1, 4));
        this.add(new JScrollPane(textbox));
        this.add(runbutton);
        this.add(new JButton("UNDO"));
        this.add(new JButton("REDO"));

    }

}
