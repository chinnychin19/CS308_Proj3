package view.inputPanel;

import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class Textbox extends JTextArea {

    public Textbox () {
        super();
        final int ROWS = 5;
        this.setRows(ROWS);

    }

    protected void clear () {
        this.setText("");
    }

    protected String getInput () {

        return this.getText();
    }

    protected void addInput (String input) {
        String newInput = getInput() + input + " ";
        this.setText(newInput);
    }

}
