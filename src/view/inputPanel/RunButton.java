package view.inputPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import view.Controller;
import view.View;
import view.modulePanel.ModulePanel;
import model.Model;


@SuppressWarnings("serial")
public class RunButton extends JButton {
    Textbox myTextbox;

    private MouseListener myMouseListener;
    private Controller myController;

    public RunButton (String title, Textbox textbox, Controller controller) {
        super(title);
        myController = controller;

        myTextbox = textbox;

        myMouseListener = new MouseListener() {

            @Override
            public void mouseClicked (MouseEvent e) {

                sendUserInput();
            }

            @Override
            public void mousePressed (MouseEvent e) {

            }

            @Override
            public void mouseReleased (MouseEvent e) {

            }

            @Override
            public void mouseEntered (MouseEvent e) {

            }

            @Override
            public void mouseExited (MouseEvent e) {

            }

        };
        this.addMouseListener(myMouseListener);

    }

    protected void executeRunCycle () {
        //
        // sendUserInput();
        // myView.updateCanvasData();
        // mySidebar.updateModules();

    }

    protected String sendUserInput () {

        String input = myTextbox.getInput();

        if (input.trim().equals("")) { return ""; }
        ((InputController) myController).executeCommand(input);
        // myView.displayError(Model.parseInput(input));
        myTextbox.clear();
        return input;

    }

}
