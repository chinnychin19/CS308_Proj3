package view.inputPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import view.View;
import view.modulePanel.ModulePanel;
import model.Model;


@SuppressWarnings("serial")
public class RunButton extends JButton {
    Textbox myTextbox;
    ModulePanel mySidebar;
    private MouseListener myMouseListener;
    private View myView;

    public RunButton (String title, Textbox textbox, ModulePanel sidebar, View view) {
        super(title);
        myView = view;
        myTextbox = textbox;
        mySidebar = sidebar;

        myMouseListener = new MouseListener() {

            @Override
            public void mouseClicked (MouseEvent e) {
                myView.runCommand();

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

        // if (input.trim().equals("")) { return ""; }
        // myView.displayError(Model.parseInput(input));
        // myTextbox.clear();
        return input;

    }

}
