package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import jgame.JGColor;
import menuBar.MenuBar;
import model.Model;
import view.display.Canvas;
import view.display.ViewUpdater;
import view.inputPanel.RunButton;
import view.inputPanel.Textbox;
import view.modulePanel.ModulePanel;
import view.optionsPanel.BackgroundColorChooser;
import view.optionsPanel.GridCheckBox;
import view.optionsPanel.ImageChooser;
import view.optionsPanel.OptionsPanel;
import view.optionsPanel.PenColorChooser;
import view.optionsPanel.StatusCheckBox;


public class View extends JFrame {
    protected ViewUpdater myViewUpdater;

    private static Canvas myCanvas;

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


        myCanvas = new Canvas();
        paramaters.put("pen", new PenColorChooser(this));
        paramaters.put("bg", new BackgroundColorChooser(this));
        paramaters.put("status", new StatusCheckBox(this));
        paramaters.put("image", new ImageChooser(this));
        paramaters.put("grid", new GridCheckBox(this));
        

        JPanel optionsPanel = PanelFactory.makePanel("option", paramaters);

        setJMenuBar(new MenuBar());
        this.getContentPane().add(modulePanel, BorderLayout.EAST);
        this.getContentPane().add(inputPanel, BorderLayout.SOUTH);
        this.getContentPane().add(optionsPanel, BorderLayout.NORTH);
        this.getContentPane().add(myCanvas, BorderLayout.CENTER);

        setVisible(true);

        Model.initModel();

    }

    public void updateCanvasData () {
        myCanvas.moveTurtle(Model.getTurtleX(), Model.getTurtleY());
        myCanvas.setHeading(Model.getTurtleAngle());
        myCanvas.setPaths(Model.getTurtlePaths());
        myCanvas.isTurtleVisible(Model.isTurtleVisible());
    }

    public void displayError (String error) {
        myCanvas.setError(error);
    }
    
    public Canvas getCanvas(){
        return myCanvas;
    }


    public static void main (String[] args) {
        new View();
    }
}
