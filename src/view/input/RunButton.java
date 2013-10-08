package view.input;

import javax.swing.JButton;


public class RunButton extends JButton {
    Textbox textbox;
    public RunButton (String title, Textbox textbox) {
        super(title);
        this.textbox = textbox;
    }

    protected String sendUserInput () {
         String input = textbox.getInput();
         textbox.clear();
         return input;
        
        
        
    }

}
