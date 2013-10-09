package view.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import model.Model;


public class RunButton extends JButton {
    Textbox textbox;

    private MouseListener myMouseListener;

    public RunButton (String title, Textbox textbox) {
        super(title);
        Model.initModel();
        myMouseListener = new MouseListener() {

            @Override
            public void mouseClicked (MouseEvent e) {
                sendUserInput();

            }

            @Override
            public void mousePressed (MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased (MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered (MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited (MouseEvent e) {
                // TODO Auto-generated method stub

            }

        };
        this.addMouseListener(myMouseListener);
        this.textbox = textbox;
    }

    protected String sendUserInput () {

        String input = textbox.getInput();
        if (input.trim().equals("")) { return ""; }
        Model.parseInput(input);
        textbox.clear();
        return input;

    }

}
