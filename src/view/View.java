package view;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import view.inputPanel.InputController;
import view.inputPanel.RunButton;
import view.inputPanel.Textbox;
import view.modulePanel.ModuleData;
import view.modulePanel.ModulePanel;
import view.modulePanel.ModulePanelController;
import view.optionsPanel.BackgroundColorChooser;
import view.optionsPanel.GridCheckBox;
import view.optionsPanel.ImageChooser;
import view.optionsPanel.OptionsPanel;
import view.optionsPanel.PenColorChooser;
import view.optionsPanel.StatusCheckBox;


public class View extends JFrame implements Observer{
    protected ViewUpdater myViewUpdater;

    private static Canvas myCanvas;
    private Model myModel;
    private ControllerDraft myController;
    JPanel modulePanel;
    Textbox textbox;
    RunButton runbutton;
    JPanel inputPanel ;
    JPanel optionsPanel;

    /**
     * Constructor for View Class
     */
    public View (Model model) {
        myModel = model;
        textbox = new Textbox(Constants.FIELD_SIZE);
        myController = new ControllerDraft(myModel, this, textbox);
        setTitle("SLogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(Constants.GUI_WIDTH, Constants.GUI_HEIGHT));
        
        Subject subject = new Subject(model, this);
        Map<String, JComponent> paramaters = new HashMap<String, JComponent>();
        Controller moduleController = new ModulePanelController(subject, model);
        paramaters.put("textbox", textbox);
        modulePanel = PanelFactory.makePanel("module", paramaters,moduleController);
        subject.addObservers((Observer) modulePanel);
        subject.addObservers((Observer) this);
        Controller inputController = new InputController(subject, model);
        runbutton = new RunButton("RUN", textbox,inputController);
        paramaters.put("runbutton", runbutton);
        inputPanel = PanelFactory.makePanel("input", paramaters,moduleController);

        myCanvas = new Canvas();
        paramaters.put("pen", new PenColorChooser(this));
        paramaters.put("bg", new BackgroundColorChooser(this));
        paramaters.put("status", new StatusCheckBox(this));
        paramaters.put("image", new ImageChooser(this, myCanvas));
        paramaters.put("grid", new GridCheckBox(this));

       optionsPanel = PanelFactory.makePanel("option", paramaters,moduleController);

        setJMenuBar(new MenuBar());
        this.getContentPane().add(modulePanel, BorderLayout.EAST);
        this.getContentPane().add(inputPanel, BorderLayout.SOUTH);
        this.getContentPane().add(optionsPanel, BorderLayout.NORTH);
        this.getContentPane().add(myCanvas, BorderLayout.CENTER);

        setVisible(true);

        // Model.initModel();

    }

    public void runCommand () {
        myController.executeCommand();
    }

    // SUSAN BEGIN COMPLETING METHODS



    protected void changeWorkSpace () {
        // TODO
        // CLEAR Textbox and everything else
    }

    protected void updateBGColor () {
        // TODO

    }

    protected void updateModulePanel (Map<String, Collection<ModuleData>> map) {
        ((ModulePanel) modulePanel).updateModules(map);
    }

    protected void updateCanvasPanel () {
        // SUSAN FIX THE INDEX VALUES PLEASE :)
        
        ArrayList<Integer> activeTurtleList = new ArrayList<Integer>(); //myModel.getActiveTurtleIDs ();
        activeTurtleList.add(1);   
        myCanvas.setActiveTurtles(activeTurtleList);
        
        //System.out.println(activeTurtleList);
        
        for (Integer ID: activeTurtleList){
           myCanvas.moveTurtle(ID, myModel.getTurtleX(ID), myModel.getTurtleY(ID));
           myCanvas.setHeading(ID, myModel.getTurtleAngle(ID));
           myCanvas.changeTurtleVisiblity(myModel.isTurtleVisible(1));
        }

        
        myCanvas.setPaths(myModel.getTurtlePaths());
        
    }

    protected void updateOptionsPanel () {
        // TODO
    }

    // SUSAN END COMPLETING METHODS :)

    protected void changeModel (Model newModel) {
        myModel = newModel;
    }
 
    
    public void displayError (String error) {
        myCanvas.setError(error);
    }

    public Canvas getCanvas () {
        return myCanvas;
    }

    @Override
    public void update (String error,
                        String updateVariable,
                        Map<String, Collection<ModuleData>> moduleMap) {
        displayError(error);
        
    }

}
