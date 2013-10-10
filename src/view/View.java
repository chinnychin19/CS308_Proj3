package view;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Model;
import view.sidebar.SidebarPanel;
import view.display.Canvas;
import view.display.ViewUpdater;
import view.input.InputPanel;
import view.input.RunButton;
import view.input.Textbox;


public class View extends JFrame {
    private static final int GUI_WIDTH = 1000;
    private static final int GUI_HEIGHT = 600;
    private static final int DISPLAY_HEIGHT = 100;
    private static final int DISPLAY_WIDTH = 300;
    protected ViewUpdater myViewUpdater;

    private static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    private static final String USER_DIR = "user.dir";
    private static final int FIELD_SIZE = 30;

    public View () {

        setTitle("SLogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(GUI_WIDTH, GUI_HEIGHT));
        Textbox textbox = new Textbox(Constants.FIELD_SIZE);
        final JPanel sidebarPanel = new SidebarPanel(textbox);

        final JPanel inputPanel = new InputPanel();
        RunButton runbutton = new RunButton("RUN", textbox, (SidebarPanel) sidebarPanel);
        inputPanel.add(textbox);
        inputPanel.add(runbutton);

        final JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(1, 0));
        
        optionsPanel.add(makeGridCheckbox());

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
    
    private Component makeGridCheckbox(){
        Component result = new Checkbox("Grid", null, true);
        
       
        return result;
    }
    
//    private ItemListener gridListener(){
//        ItemListener listener = new ItemListener();
//        return listener;
//    }

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
        String input = "";
        Model.parseInput(input);
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

    public static void main (String[] args) {
        new View();
    }
}
