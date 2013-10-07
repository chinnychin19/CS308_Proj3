package view.input;

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JTextField;

public class Textbox extends JTextField {
    
    private ActionListener myActionListener;
    private KeyListener myKeyListener;
    private MouseListener myMouseListener;
    private FocusListener myFocusListener;
    
    public Textbox(int fieldSize){
        super(fieldSize);
        this.addKeyListener(myKeyListener);
        this.addFocusListener(myFocusListener);
        this.addActionListener(myActionListener);
        this.addMouseListener(myMouseListener);
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
