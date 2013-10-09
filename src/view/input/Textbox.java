package view.input;

import javax.swing.JTextField;


public class Textbox extends JTextField {

    public Textbox (int fieldSize) {
        super(fieldSize);

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
