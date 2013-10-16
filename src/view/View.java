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
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import jgame.JGColor;
import model.Model;
import view.display.Canvas;
import view.display.ViewUpdater;
import view.inputPanel.InputPanel;
import view.inputPanel.RunButton;
import view.inputPanel.Textbox;
import view.modulePanel.ModulePanel;


public class View extends JFrame {
    protected ViewUpdater myViewUpdater;

    private static Canvas viewCanvas;

    /**
     * Constructor for View Class
     */
    public View () {

        setTitle("SLogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(Constants.GUI_WIDTH, Constants.GUI_HEIGHT));

        Map<String, JComponent> paramaters = new HashMap<String, JComponent>();

        Textbox textbox = new Textbox(Constants.FIELD_SIZE);

        paramaters.put("textbox", textbox);
        JPanel modulePanel = PanelFactory.makePanel("module", paramaters);
        RunButton runbutton = new RunButton("RUN", textbox, (ModulePanel) modulePanel, this);
        paramaters.put("runbutton", runbutton);
        JPanel inputPanel = PanelFactory.makePanel("input", paramaters);

        final JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(1, 0));

        viewCanvas = new Canvas();

        optionsPanel.add(makeGridCheckbox());

        // optionsPanel.add(makeTurtleCheckBox());
        optionsPanel.add(makeStatusCheckBox());


        // optionsPanel.add(new Checkbox("Pen Down", null, true));
        optionsPanel.add(penColorChooser());
        optionsPanel.add(makeBackgroundChooser());
        optionsPanel.add(makeImageChooserButton());
        optionsPanel.add(makeHelpButton());

        this.getContentPane().add(modulePanel, BorderLayout.EAST);
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
        viewCanvas.isTurtleVisible(Model.isTurtleVisible());
    }

    public void displayError (String error) {
        viewCanvas.setError(error);
    }

    private JButton penColorChooser () {
        JButton result = new JButton("Change Pen Color");

        result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                Color newColor =
                        JColorChooser.showDialog(null, "Choose a new pen color", Color.red);
                viewCanvas.changePenColor(new JGColor(newColor.getRed(),
                                                      newColor.getGreen(), newColor
                                                              .getBlue()));
            }

        });

        return result;
    }

    /**
     * Creates button to pop up color choosing dialogue
     * 
     * @return JButton with label "Change Color"
     */
    private JButton makeBackgroundChooser () {
        JButton result = new JButton("Change BG Color");
        result.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed (ActionEvent e) {
                Color newColor =
                        JColorChooser.showDialog(null, "Choose a new background color", Color.red);
                viewCanvas.changeBackgroundColor(new JGColor(newColor.getRed(),
                                                             newColor.getGreen(), newColor
                                                                     .getBlue()));
            }

        });
        return result;
    }


    /**
     * Checkbox for status
     * 
     * @return
     */
    private JCheckBox makeStatusCheckBox () {
        JCheckBox result = new JCheckBox("Turtle Status", null, true);
        result.addItemListener(new ItemListener() {

            public void itemStateChanged (ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    viewCanvas.toggleStatus();
                }
                else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    viewCanvas.toggleStatus();
                }
            }

        });

        return result;
    }

    /**
     * Class that makes checkbox for Grid
     * 
     * @return Checkbox that toggles grid
     */
    private JCheckBox makeGridCheckbox () {
        JCheckBox result = new JCheckBox("Grid", null, false);
        result.addItemListener(new ItemListener() {

            public void itemStateChanged (ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    viewCanvas.toggleGrid();
                }
                else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    viewCanvas.toggleGrid();
                }
            }

        });

        return result;
    }

    /**
     * Dropdown box for choosing image
     * 
     * @return
     */
    private JComboBox<?> makeImageChooserButton () {
        String[] turtleOptions = { "Turtle1.gif", "Turtle2.gif", "Turtle3.gif" };
        JComboBox<?> result = new JComboBox(turtleOptions);
        result.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed (ActionEvent e) {
                JComboBox<?> cb = (JComboBox) e.getSource();
                String turtleSelection = (String) cb.getSelectedItem();
                viewCanvas.changeTurtleImage(turtleSelection);
            }

        });

        return result;
    }

    /**
     * Method that creates button to open html help page
     * 
     * @return button with label "Help Me"
     */
    private JComponent makeHelpButton () {
        JButton result = new JButton("Help Me");
        result.addActionListener(new ActionListener() {

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

        });
        return result;
    }

    protected void sendInput () {
        String input = "";
        Model.parseInput(input);
    }

    private void updateDisplay () {
        myViewUpdater.displayOutput();
    }

    public static void main (String[] args) {
        new View();
    }
}
