package view.input;

import javax.swing.JTextArea;


public class Textbox extends JTextArea {

    public Textbox (int fieldSize) {
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

    public void addInput (String input) {
        String newInput = getInput() + input + " ";
        this.setText(newInput);
    }

}
