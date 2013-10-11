package view.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import view.View;
import view.sidebar.SidebarPanel;
import model.Model;


public class RunButton extends JButton {
    Textbox textbox;
    SidebarPanel sidebar;
    private MouseListener myMouseListener;
    private View view;

    public RunButton (String title, Textbox textbox, SidebarPanel sidebar, View view) {
        super(title);
        this.view = view;
        this.textbox = textbox;
        this.sidebar = sidebar;
        Model.initModel();
        myMouseListener = new MouseListener() {

            @Override
            public void mouseClicked (MouseEvent e) {

                executeRunCycle();

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

        sendUserInput();
        view.updateCanvasData();
        sidebar.updateModules();

    }

    protected String sendUserInput () {

        String input = textbox.getInput();

        if (input.trim().equals("")) { return ""; }
        view.displayError(Model.parseInput(input)); //Errors need to be implemented on Model side
        textbox.clear();
        return input;

    }

}
