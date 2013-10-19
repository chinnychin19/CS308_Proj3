package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import menuBar.MenuBar;
import model.Model;
import view.display.Canvas;
import view.display.CanvasSubject;
import view.inputPanel.InputController;
import view.inputPanel.Textbox;
import view.modulePanel.ModuleObserver;
import view.modulePanel.ModulePanelController;
import view.modulePanel.ModuleSubject;
import view.optionsPanel.BackgroundColorChooser;
import view.optionsPanel.GridCheckBox;
import view.optionsPanel.ImageChooser;
import view.optionsPanel.PenColorChooser;
import view.optionsPanel.StatusCheckBox;
import view.workspace.WorkSpaceSelector;


public class View extends JFrame {

    private Model myModel;

    private WorkSpaceSelector selector;

    /**
     * Constructor for View Class
     */
    public View () {
        Canvas myCanvas = new Canvas();
        Map<String, JComponent> paramaters = new HashMap<String, JComponent>();
        myModel = new Model();
        List<Controller> controllers = new ArrayList<Controller>();
        List<MasterSubject> subjects = new ArrayList<MasterSubject>();

        MasterSubject subject = new MasterSubject(myModel);
        subjects.add(subject);

        Textbox textbox = new Textbox();
        addParameters(paramaters, myCanvas, textbox);

        setJMenuBar(new MenuBar());

        initializeDisplaySettings();

        makePanels(myCanvas, paramaters, controllers, subjects, subject, textbox);
        setVisible(true);

    }

    private void makePanels (Canvas myCanvas,
                             Map<String, JComponent> paramaters,
                             List<Controller> controllers,
                             List<MasterSubject> subjects,
                             MasterSubject subject,
                             Textbox textbox) {
        Controller moduleController = new ModulePanelController(subject, myModel);
        controllers.add(moduleController);

        JPanel modulePanel = PanelFactory.makePanel("module", paramaters, moduleController);

        ModuleSubject myModuleSubject = new ModuleSubject(myModel);
        myModuleSubject.addObservers((ModuleObserver) modulePanel);
        subject.addSubject(myModuleSubject);

        CanvasSubject myCanvasSubject = new CanvasSubject(myModel);
        myCanvasSubject.addObservers(myCanvas);
        subject.addSubject(myCanvasSubject);

        Controller inputController = new InputController(subject, myModel, textbox);
        controllers.add(inputController);
        JPanel inputPanel = PanelFactory.makePanel("input", paramaters, inputController);

        JPanel optionsPanel = PanelFactory.makePanel("option", paramaters, null);
        selector = new WorkSpaceSelector(controllers, subjects, myModel);
        JButton showItButton = new JButton("Select Workspace");
        showItButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed (ActionEvent e) {
                selector.NewWorkSpace();

            }

        });
        optionsPanel.add(showItButton);

        addPanelsToLayout(myCanvas, modulePanel, inputPanel, optionsPanel);
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

    private void addParameters (Map<String, JComponent> paramaters, Canvas canvas, Textbox textbox) {
        paramaters.put("textbox", textbox);
        paramaters.put("pen", new PenColorChooser(this, canvas));
        paramaters.put("bg", new BackgroundColorChooser(this, canvas));
        paramaters.put("status", new StatusCheckBox(this, canvas));
        paramaters.put("image", new ImageChooser(this, canvas));
        paramaters.put("grid", new GridCheckBox(this, canvas));
    }

    protected void changeModel (Model newModel) {
        myModel = newModel;
    }

}
