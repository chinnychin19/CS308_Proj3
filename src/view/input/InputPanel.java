package view.input;

import java.awt.GridLayout;
import javax.swing.JPanel;
import view.Constants;


public class InputPanel extends JPanel {
    Textbox textbox;
    RunButton runbutton;

    public InputPanel () {
        super();
        textbox = new Textbox(Constants.FIELD_SIZE);
        runbutton = new RunButton("RUN");
        this.setLayout(new GridLayout(1, 1));
        this.add(textbox);
        this.add(runbutton);
    }

}
