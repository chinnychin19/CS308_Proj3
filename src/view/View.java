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
import menuBar.MenuBarController;
import model.Model;
import view.display.Canvas;
import view.display.CanvasSubject;
import view.inputPanel.InputController;
import view.inputPanel.InputObserver;
import view.inputPanel.InputPanel;
import view.inputPanel.InputSubject;
import view.modulePanel.ModuleObserver;
import view.modulePanel.ModulePanel;
import view.modulePanel.ModulePanelController;
import view.modulePanel.ModuleSubject;
import view.optionsPanel.OptionsPanel;
import view.optionsPanel.OptionsPanelController;
import view.workspace.WorkSpacePreferences;
import view.workspace.WorkSpacePreferencesController;


@SuppressWarnings("serial")
public class View extends JFrame {

    private Model myModel;

    private WorkSpacePreferences selector;
    private MasterSubject subject;

    /**
     * Constructor for View Class
     */
    public View () {

        Canvas myCanvas = new Canvas();
        Map<String, JComponent> paramaters = new HashMap<String, JComponent>();
        myModel = new Model();
        List<Controller> controllers = new ArrayList<Controller>();
        List<Subject> subjects = new ArrayList<Subject>();

        subject = new MasterSubject(myModel);
        subjects.add(subject);

        initializeDisplaySettings();

        makePanels(myCanvas, paramaters, controllers, subjects, subject);
        setVisible(true);

    }

    private void makePanels (Canvas myCanvas,
                             Map<String, JComponent> paramaters,
                             List<Controller> controllers,
                             List<Subject> subjects,
                             MasterSubject subject)

    {
        JTextArea textbox = new JTextArea();
        textbox.setRows(Constants.TEXTBOX_ROWS);
        paramaters.put("textbox", textbox);
        JPanel modulePanel = addModulePanel(controllers, subject, textbox);

        addCanvas(myCanvas, subject);

        JPanel inputPanel = addInputController(controllers, subject, textbox);

        JPanel optionsPanel = addOptionsPanel(myCanvas, controllers, subject);

        addMenu(controllers, subjects, subject);

        addPanelsToLayout(myCanvas, modulePanel, inputPanel, optionsPanel);
    }

    private void addMenu (List<Controller> controllers,
                          List<Subject> subjects,
                          MasterSubject subject) {
        WorkSpacePreferencesController wokspaceController =
                new WorkSpacePreferencesController(subject, controllers, subjects, myModel);
        selector = new WorkSpacePreferences(wokspaceController);

        MenuBarController menuController = new MenuBarController(subject, myModel);
        controllers.add(menuController);
        MenuBar menu = new MenuBar(menuController);
        menu.add("selector", selector);
        setJMenuBar(menu);
    }
    //Observed/Subject  - sends
    //Observer/Subscriber 
    private JPanel addOptionsPanel (Canvas myCanvas,
                                    List<Controller> controllers,
                                    MasterSubject subject) {
        OptionsPanelController optionsController =
                new OptionsPanelController(subject, myModel, myCanvas);
        controllers.add(optionsController);
        JPanel optionsPanel = new OptionsPanel(optionsController);
        return optionsPanel;
    }

    private JPanel addInputController (List<Controller> controllers,
                                       MasterSubject subject,
                                       JTextArea textbox) {
        InputController inputController = new InputController(subject, myModel, textbox);
        controllers.add(inputController);
        JPanel inputPanel = new InputPanel(textbox, inputController);
        InputSubject inputSubject = new InputSubject(myModel, (InputObserver) inputPanel);
        subject.addSubject(inputSubject);
        return inputPanel;
    }

    private void addCanvas (Canvas myCanvas, MasterSubject subject) {
        CanvasSubject myCanvasSubject = new CanvasSubject(myModel, myCanvas);
        subject.addSubject(myCanvasSubject);
    }

    private JPanel addModulePanel (List<Controller> controllers,
                                MasterSubject subject,
                                JTextArea textbox) {
        Controller moduleController = new ModulePanelController(subject, myModel, textbox);
        controllers.add(moduleController);

        JPanel modulePanel = new ModulePanel(moduleController);
        ModuleSubject myModuleSubject = new ModuleSubject(myModel, (ModuleObserver) modulePanel);
        subject.addSubject(myModuleSubject);
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

    protected void changeModel (Model newModel) {
        myModel = newModel;
    }

}
