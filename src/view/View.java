package view;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.Model;
import view.sidebar.CommandsModule;
import view.sidebar.HistoryModule;
import view.sidebar.SidebarPanel;
import view.sidebar.VariableModule;
import view.display.ViewUpdater;
import view.input.InputPanel;
import view.input.RunButton;
import view.input.Textbox;


public class View extends JFrame {
    private static final int GUI_WIDTH = 1000;
    private static final int GUI_HEIGHT = 600;
    protected ViewUpdater myViewUpdater;
    protected RunButton myRunButton;
    protected Textbox myTextbox;



    public View () {

        setTitle("SLogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(GUI_WIDTH, GUI_HEIGHT));

        final JPanel sidebarPanel = new SidebarPanel();
        final JPanel optionsPanel = new JPanel();
        final JPanel inputPanel = new InputPanel();
        
        optionsPanel.setLayout(new GridLayout(1, 0));
        optionsPanel.add(new Checkbox("Grid", null, true));

        final JComboBox backgroundChooser = new JComboBox();
        backgroundChooser.addItem("COLOR");
        optionsPanel.add(backgroundChooser);

        optionsPanel.add(new Checkbox("Pen Down", null, true));

        optionsPanel.add(makeImageChooserButton());
        optionsPanel.add(makeHelpButton());
        // initEngineComponent will embed it
        this.getContentPane().add(sidebarPanel, BorderLayout.EAST);
        this.getContentPane().add(inputPanel, BorderLayout.SOUTH);
        this.getContentPane().add(optionsPanel, BorderLayout.NORTH);
        // this.getContentPane().add(new Canvas(), BorderLayout.CENTER);

        setVisible(true);

        Model.initModel();
    }

    private JComponent makeImageChooserButton () {
        JButton result = new JButton("Image");
        return result;
    }

    private JComponent makeHelpButton () {
        JButton result = new JButton("Help Me");
        return result;
    }

    // private JComponent makeDisplay () {
    // JPanel display = new Display(DISPLAY_WIDTH, DISPLAY_HEIGHT);
    // return display;
    // }


    protected void sendInput () {
        // when runButton is activated
        // input is sent to model
        String input = "";
        Model.parseInput(input);
        // Clear textbox
    }

    protected void executeInput () {
        while (Model.hasNextInstruction()) {
            Model.processNextInstruction();
            updateDisplay();
            updateSidebar();

        }

    }

    private void updateSidebar () {

    }

    private void updateDisplay () {
        myViewUpdater.displayOutput();
    }

}
