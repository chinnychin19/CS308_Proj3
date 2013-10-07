package view.input;

import javax.swing.JTextField;


public class Textbox extends JTextField {

    public Textbox (int fieldSize) {
        super(fieldSize);

    }

    public void clear () {
        this.setText("");
    }

    public String getInput () {

        return this.getText().trim();
    }

    public void addInput (String input) {
        String newInput = getInput() + input;
        this.setText(newInput);
    }

}
