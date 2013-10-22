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
import javax.swing.JTextArea;
import menuBar.MenuBar;
import model.Model;
import view.display.Canvas;
import view.display.CanvasSubject;
import view.inputPanel.InputPanel;
import view.modulePanel.ModulePanel;
import view.optionsPanel.OptionsPanel;
import view.workspace.WorkSpacePreferences;


@SuppressWarnings("serial")
public class View extends JFrame {

    private Model myModel;

    private WorkSpacePreferences selector;

    private ViewController controller;
    private List<Subject> subjects = new ArrayList<Subject>();
    private List<Updatable> updatables = new ArrayList<Updatable>();

    // private Model myCurrentModel;

    /**
     * Constructor for View Class
     */
    public View () {

        Canvas myCanvas = new Canvas(controller);
        Map<String, JComponent> paramaters = new HashMap<String, JComponent>();
        myModel = new Model();

        initializeDisplaySettings();

        makePanels(myCanvas, paramaters, subjects);
        setVisible(true);

    }

    private void makePanels (Canvas myCanvas,
                             Map<String, JComponent> paramaters,

                             List<Subject> subjects)

    {
        JTextArea textbox = new JTextArea();
        textbox.setRows(Constants.TEXTBOX_ROWS);
        paramaters.put("textbox", textbox);
        controller =
                new ViewController(myModel, textbox, myCanvas, this);
        JPanel modulePanel = addModulePanel(controller, textbox);

        addCanvas(myCanvas);

        JPanel inputPanel = addInputPanel(textbox, controller);

        JPanel optionsPanel = addOptionsPanel(myCanvas, controller);

        addMenu(controller, subjects);

        addPanelsToLayout(myCanvas, modulePanel, inputPanel, optionsPanel);
    }

    private void addMenu (ViewController controller,
                          List<Subject> subjects) {

        selector = new WorkSpacePreferences(controller);

        MenuBar menu = new MenuBar(controller);
        menu.add("selector", selector);
        setJMenuBar(menu);
    }

    private JPanel addOptionsPanel (Canvas myCanvas,

                                    ViewController controller) {

        JPanel optionsPanel = new OptionsPanel(controller);
        return optionsPanel;
    }

    private JPanel addInputPanel (

                                  JTextArea textbox, ViewController controller) {

        JPanel inputPanel = new InputPanel(textbox, controller, myModel);
        updatables.add((Updatable) inputPanel);
        // InputSubject inputSubject = new InputSubject(myModel, (InputObserver) inputPanel);
        // subjects.add(inputSubject);
        return inputPanel;
    }

    private void addCanvas (Canvas myCanvas) {
        CanvasSubject myCanvasSubject = new CanvasSubject(myModel, myCanvas);
        subjects.add(myCanvasSubject);
    }

    private JPanel addModulePanel (ViewController controller,

                                   JTextArea textbox) {

        JPanel modulePanel = new ModulePanel(controller, myModel);
        updatables.add((Updatable) modulePanel);
        // ModuleSubject myModuleSubject = new ModuleSubject(myModel, (ModuleObserver) modulePanel);
        // subjects.add(myModuleSubject);
        return modulePanel;
    }

    private void initializeDisplaySettings () {
        setTitle("SLogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(Constants.GUI_WIDTH, Constants.GUI_HEIGHT));
    }

    private void addPanelsToLayout (Canvas canvas, JPanel modulePanel, JPanel inputPanel,
                                    JPanel optionsPanel) {
        this.getContentPane().add(modulePanel, BorderLayout.EAST);
        this.getContentPane().add(inputPanel, BorderLayout.SOUTH);
        this.getContentPane().add(optionsPanel, BorderLayout.NORTH);
        this.getContentPane().add(canvas, BorderLayout.CENTER);

    }

    // protected void changeModel (Model newModel) {
    // myModel = newModel;
    // }

    public void notifyObservers (String error) {

        for (Subject subject : subjects) {
            subject.notifyObservers(error);
        }
        for (Updatable updateable : updatables) {
            updateable.update();
        }

    }

    public void changeCurrentModel (Model model) {

        for (Subject subject : subjects) {
            subject.changeCurrentModel(model);

        }
        for (Updatable updateable : updatables) {
            updateable.changeModel(model);
        }

    }
}
