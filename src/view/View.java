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
import view.inputPanel.InputObserver;
import view.inputPanel.InputPanel;
import view.inputPanel.InputSubject;
import view.modulePanel.ModuleObserver;
import view.modulePanel.ModulePanel;
import view.modulePanel.ModuleSubject;
import view.optionsPanel.OptionsPanel;
import view.workspace.WorkSpacePreferences;


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

        List<Subject> subjects = new ArrayList<Subject>();

        subject = new MasterSubject(myModel);
        subjects.add(subject);

        initializeDisplaySettings();

        makePanels(myCanvas, paramaters, subjects, subject);
        setVisible(true);

    }

    private void makePanels (Canvas myCanvas,
                             Map<String, JComponent> paramaters,

                             List<Subject> subjects,
                             MasterSubject subject)

    {
        JTextArea textbox = new JTextArea();
        textbox.setRows(Constants.TEXTBOX_ROWS);
        paramaters.put("textbox", textbox);
        ViewController controller =
                new ViewController(subject, myModel, textbox, myCanvas, subjects);
        JPanel modulePanel = addModulePanel(controller, subject, textbox);

        addCanvas(myCanvas, subject);

        JPanel inputPanel = addInputPanel(subject, textbox, controller);

        JPanel optionsPanel = addOptionsPanel(myCanvas, controller);

        addMenu(controller, subjects, subject);

        addPanelsToLayout(myCanvas, modulePanel, inputPanel, optionsPanel);
    }

    private void addMenu (ViewController controller,
                          List<Subject> subjects,
                          MasterSubject subject) {

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
                                  MasterSubject subject,
                                  JTextArea textbox, ViewController controller) {

        JPanel inputPanel = new InputPanel(textbox, controller);
        InputSubject inputSubject = new InputSubject(myModel, (InputObserver) inputPanel);
        subject.addSubject(inputSubject);
        return inputPanel;
    }

    private void addCanvas (Canvas myCanvas, MasterSubject subject) {
        CanvasSubject myCanvasSubject = new CanvasSubject(myModel, myCanvas);
        subject.addSubject(myCanvasSubject);
    }

    private JPanel addModulePanel (ViewController controller,
                                   MasterSubject subject,
                                   JTextArea textbox) {

        JPanel modulePanel = new ModulePanel(controller);
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
