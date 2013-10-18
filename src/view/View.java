package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import menuBar.MenuBar;
import model.Model;
import view.display.Canvas;
import view.display.ViewUpdater;
import view.inputPanel.InputController;
import view.inputPanel.RunButton;
import view.inputPanel.Textbox;
import view.modulePanel.ModulePanelController;
import view.optionsPanel.BackgroundColorChooser;
import view.optionsPanel.GridCheckBox;
import view.optionsPanel.ImageChooser;
import view.optionsPanel.PenColorChooser;
import view.optionsPanel.StatusCheckBox;


public class View extends JFrame {
    protected ViewUpdater myViewUpdater;

    private static Canvas myCanvas;
    private Model myModel;
    JPanel modulePanel;
    Textbox textbox;
    RunButton runbutton;
    JPanel inputPanel;
    JPanel optionsPanel;

    /**
     * Constructor for View Class
     */
    public View (Model model) {
        myModel = model;
        textbox = new Textbox(Constants.FIELD_SIZE);

        setTitle("SLogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(Constants.GUI_WIDTH, Constants.GUI_HEIGHT));

        Subject subject = new Subject(model, this);
        Map<String, JComponent> paramaters = new HashMap<String, JComponent>();
        Controller moduleController = new ModulePanelController(subject, model);
        paramaters.put("textbox", textbox);
        modulePanel = PanelFactory.makePanel("module", paramaters, moduleController);
        subject.addObservers((Observer) modulePanel);
        subject.addObservers((Observer) this);
        Controller inputController = new InputController(subject, model);
        runbutton = new RunButton("RUN", textbox, inputController);
        paramaters.put("runbutton", runbutton);
        inputPanel = PanelFactory.makePanel("input", paramaters, moduleController);

        myCanvas = new Canvas();
        subject.addObservers((Observer) myCanvas);
        paramaters.put("pen", new PenColorChooser(this));
        paramaters.put("bg", new BackgroundColorChooser(this));
        paramaters.put("status", new StatusCheckBox(this));
        paramaters.put("image", new ImageChooser(this, myCanvas));
        paramaters.put("grid", new GridCheckBox(this));

        optionsPanel = PanelFactory.makePanel("option", paramaters, moduleController);

        setJMenuBar(new MenuBar());
        this.getContentPane().add(modulePanel, BorderLayout.EAST);
        this.getContentPane().add(inputPanel, BorderLayout.SOUTH);
        this.getContentPane().add(optionsPanel, BorderLayout.NORTH);
        this.getContentPane().add(myCanvas, BorderLayout.CENTER);

        setVisible(true);

    }

    protected void changeModel (Model newModel) {
        myModel = newModel;
    }

    public Canvas getCanvas () {
        return myCanvas;
    }

}
