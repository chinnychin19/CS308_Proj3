package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import menuBar.MenuBar;
import model.Model;
import view.display.Canvas;
import view.display.ViewUpdater;
import view.inputPanel.InputController;
import view.inputPanel.Textbox;
import view.modulePanel.ModulePanelController;
import view.optionsPanel.BackgroundColorChooser;
import view.optionsPanel.GridCheckBox;
import view.optionsPanel.ImageChooser;
import view.optionsPanel.PenColorChooser;
import view.optionsPanel.StatusCheckBox;


public class View extends JFrame {
    protected ViewUpdater myViewUpdater;

    private Canvas myCanvas;
    private Model myModel;
    private JPanel modulePanel;
    private Textbox textbox;
    private JPanel inputPanel;
    private JPanel optionsPanel;

    /**
     * Constructor for View Class
     */
    public View (Model model) {
        Map<String, JComponent> paramaters = new HashMap<String, JComponent>();
        List<Model> models = new ArrayList<Model>();
        myModel = model;

        setTitle("SLogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(Constants.GUI_WIDTH, Constants.GUI_HEIGHT));

        Subject subject = new Subject(model, this);

        myCanvas = new Canvas();
        subject.addObservers((Observer) myCanvas);

        textbox = new Textbox();
        addParameters(paramaters);

        Controller moduleController = new ModulePanelController(subject, model);
        modulePanel = PanelFactory.makePanel("module", paramaters, moduleController);
        subject.addObservers((Observer) modulePanel);

        Controller inputController = new InputController(subject, model, textbox);
        inputPanel = PanelFactory.makePanel("input", paramaters, inputController);

        optionsPanel = PanelFactory.makePanel("option", paramaters, moduleController);

        setJMenuBar(new MenuBar());
        addPanelsToLayout();
        setVisible(true);

    }

    private void addPanelsToLayout () {
        this.getContentPane().add(modulePanel, BorderLayout.EAST);
        this.getContentPane().add(inputPanel, BorderLayout.SOUTH);
        this.getContentPane().add(optionsPanel, BorderLayout.NORTH);
        this.getContentPane().add(myCanvas, BorderLayout.CENTER);

    }

    private void addParameters (Map<String, JComponent> paramaters) {
        paramaters.put("textbox", textbox);
        paramaters.put("pen", new PenColorChooser(this, myCanvas));
        paramaters.put("bg", new BackgroundColorChooser(this, myCanvas));
        paramaters.put("status", new StatusCheckBox(this, myCanvas));
        paramaters.put("image", new ImageChooser(this, myCanvas));
        paramaters.put("grid", new GridCheckBox(this, myCanvas));
    }

    protected void changeModel (Model newModel) {
        myModel = newModel;
    }

}
