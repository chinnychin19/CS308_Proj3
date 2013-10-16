package view.inputPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import view.View;
import view.modulePanel.ModulePanel;
import model.Model;


@SuppressWarnings("serial")
public class RunButton extends JButton {
    Textbox textbox;
    ModulePanel sidebar;
    private MouseListener myMouseListener;
    private View view;

    public RunButton (String title, Textbox textbox, ModulePanel sidebar, View view) {
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
        view.displayError(Model.parseInput(input));
        textbox.clear();
        return input;

    }

}
