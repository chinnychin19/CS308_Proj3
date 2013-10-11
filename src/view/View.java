package view;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import jgame.JGColor;
import model.Model;
import view.sidebar.SidebarPanel;
import view.display.Canvas;
import view.display.ViewUpdater;
import view.input.InputPanel;
import view.input.RunButton;
import view.input.Textbox;


public class View extends JFrame {
    protected ViewUpdater myViewUpdater;

    private static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    private static final String USER_DIR = "user.dir";
    private static final int FIELD_SIZE = 30;

    private static Canvas viewCanvas;

    /**
     * Constructor for View Class
     */
    public View () {

        setTitle("SLogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(Constants.GUI_WIDTH, Constants.GUI_HEIGHT));
        Textbox textbox = new Textbox(Constants.FIELD_SIZE);
        final JPanel sidebarPanel = new SidebarPanel(textbox);

        final JPanel inputPanel = new InputPanel();
        RunButton runbutton = new RunButton("RUN", textbox, (SidebarPanel) sidebarPanel, this);
        inputPanel.add(new JScrollPane(textbox));
        inputPanel.add(runbutton);

        final JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(1, 0));

        viewCanvas = new Canvas();

        optionsPanel.add(makeGridCheckbox());

        optionsPanel.add(makeTurtleCheckBox());
        optionsPanel.add(statusCheckBox());

        // optionsPanel.add(new Checkbox("Pen Down", null, true));
        optionsPanel.add(penColorChooser());
        optionsPanel.add(makeBackgroundChooser());
        optionsPanel.add(makeImageChooserButton());
        optionsPanel.add(makeHelpButton());

        this.getContentPane().add(sidebarPanel, BorderLayout.EAST);
        this.getContentPane().add(inputPanel, BorderLayout.SOUTH);
        this.getContentPane().add(optionsPanel, BorderLayout.NORTH);
        this.getContentPane().add(viewCanvas, BorderLayout.CENTER);

        setVisible(true);

        Model.initModel();

    }

    public void updateCanvasData () {
        viewCanvas.moveTurtle(Model.getTurtleX(), Model.getTurtleY());
        viewCanvas.setHeading(Model.getTurtleAngle());
        viewCanvas.setPaths(Model.getTurtlePaths());
    }
    
    public void displayError(String error){
        viewCanvas.setError(error);
    }

    private JButton penColorChooser () {
        JButton result = new JButton("Change Pen Color");
        result.addActionListener(penColorListener());
        return result;
    }

    /**
     * Method that creates ActionListener for backgroundChooser button
     * 
     * @return ActionLitener for backgroundListener
     */
    private ActionListener penColorListener () {
        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed (ActionEvent e) {
                Color newColor =
                        JColorChooser.showDialog(null, "Choose a new pen color", Color.red);
                viewCanvas.changePenColor(new JGColor(newColor.getRed(),
                                                      newColor.getGreen(), newColor
                                                              .getBlue()));
            }

        };

        return listener;
    }

    /**
     * Creates button to pop up color choosing dialogue
     * 
     * @return JButton with label "Change Color"
     */
    private JButton makeBackgroundChooser () {
        JButton result = new JButton("Change BG Color");
        result.addActionListener(backgroundListener());
        return result;
    }

    /**
     * Method that creates ActionListener for backgroundChooser button
     * 
     * @return ActionLitener for backgroundListener
     */
    private ActionListener backgroundListener () {
        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed (ActionEvent e) {
                Color newColor =
                        JColorChooser.showDialog(null, "Choose a new background color", Color.red);
                viewCanvas.changeBackgroundColor(new JGColor(newColor.getRed(),
                                                             newColor.getGreen(), newColor
                                                                     .getBlue()));
            }

        };

        return listener;
    }

    /**
     * Method that returns checkbox to toggle if turtle is visible
     * 
     * @return
     */
    private JCheckBox makeTurtleCheckBox () {
        JCheckBox result = new JCheckBox("Turtle on Screen?", null, true);
        result.addItemListener(turtleListener());
        return result;
    }

    /**
     * Listener to check if turtle is on screen
     */
    private ItemListener turtleListener () {
        ItemListener listener = new ItemListener() {

            public void itemStateChanged (ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // TODO: Make this less hardcoded
                    viewCanvas.changeTurtleImage("Turtle1.gif");
                }
                else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    viewCanvas.changeTurtleImage("Invisible.gif");
                }
            }

        };

        return listener;
    }

    /**
     * Checkbox for status
     * 
     * @return
     */
    private JCheckBox statusCheckBox () {
        JCheckBox result = new JCheckBox("Turtle Status", null, true);
        result.addItemListener(statusListener());

        return result;
    }

    private ItemListener statusListener () {
        ItemListener listener = new ItemListener() {

            public void itemStateChanged (ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    viewCanvas.toggleStatus();
                }
                else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    viewCanvas.toggleStatus();
                }
            }

        };

        return listener;
    }

    /**
     * Class that makes checkbox for Grid
     * 
     * @return Checkbox that toggles grid
     */
    private JCheckBox makeGridCheckbox () {
        JCheckBox result = new JCheckBox("Grid", null, false);
        result.addItemListener(gridListener());

        return result;
    }

    /**
     * Method that returns an ItemListener meant to be used with the Grid Checkbox. If the
     * checkbox is toggled, the status of the grid is toggled within the canvas class
     * 
     * @return
     */
    private ItemListener gridListener () {
        ItemListener listener = new ItemListener() {

            public void itemStateChanged (ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    viewCanvas.toggleGrid();
                }
                else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    viewCanvas.toggleGrid();
                }
            }

        };

        return listener;
    }

    /**
     * Dropdown box for choosing image
     * 
     * @return
     */
    private JComboBox<?> makeImageChooserButton () {
        String[] turtleOptions = { "Turtle1.gif", "Turtle2.gif", "Turtle3.gif" };
        JComboBox<?> result = new JComboBox(turtleOptions);
        result.addActionListener(imageListener());

        return result;
    }

    /**
     * Action Listener for image dropdown box
     * 
     * @return
     */
    private ActionListener imageListener () {
        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed (ActionEvent e) {
                JComboBox<?> cb = (JComboBox) e.getSource();
                String turtleSelection = (String) cb.getSelectedItem();
                viewCanvas.changeTurtleImage(turtleSelection);
            }

        };

        return listener;
    }

    /**
     * Method that creates button to open html help page
     * 
     * @return button with label "Help Me"
     */
    private JComponent makeHelpButton () {
        JButton result = new JButton("Help Me");
        result.addActionListener(helpListener());
        return result;
    }

    /**
     * Method that creates action listener for help button
     * 
     * @return
     */
    private ActionListener helpListener () {
        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed (ActionEvent e) {
                String helpPage =
                        "http://www.cs.duke.edu/courses/compsci308/current/assign/03_slogo/commands.php";
                try {
                    java.awt.Desktop.getDesktop().browse(java.net.URI.create(helpPage));
                }
                catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

        };

        return listener;
    }

    // private JComponent makeDisplay () {
    // JPanel display = new Display(DISPLAY_WIDTH, DISPLAY_HEIGHT);
    // return display;
    // }

    protected void sendInput () {
        String input = "";
        Model.parseInput(input);
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
