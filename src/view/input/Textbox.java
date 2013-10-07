package view.input;

import javax.swing.JTextField;

public class Textbox extends JTextField {
    
    public Textbox(int fieldSize){
        super(fieldSize);
        this.addKeyListener(myKeyListener);
        this.addFocusListener(myFocusListener);
        this.addActionListener(myActionListener);
    }
    protected void clear () {
        // TODO: Method that clears textbox
    }

    protected String getInput () {
        // TODO: Method that returns what is currently in textbox
        return "";
    }

    protected void addInput (String input) {
        // TODO: Method that takes in a string from history and adds to textbox
    }

}
