package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import model.Model;
import view.display.Canvas;
import view.inputPanel.InputPanel;
import view.menuBar.MenuBar;
import view.menuBar.workspace.WorkSpacePreferences;
import view.optionsPanel.OptionsPanel;
import view.sidebarPanel.InputDisplayPanel;


@SuppressWarnings("serial")
public class View extends JFrame {

    private Model myModel;

    private ViewController controller;
    private List<UpdatableDisplay> updatables = new ArrayList<UpdatableDisplay>();

    /**
     * Constructor for View Class
     */
    public View () {

        myModel = new Model();
        Canvas myCanvas = new Canvas(myModel);
        JTextArea textbox = new JTextArea();
        textbox.setRows(Constants.TEXTBOX_ROWS);
        controller = new ViewController(myModel, textbox, myCanvas, this);
        myCanvas.addController(controller);
        updatables.add(myCanvas);
        initializeDisplaySettings();
        makePanels(myCanvas, textbox);
        addMenu(controller);
        setVisible(true);

    }

    /**
     * Creates panels for the GUI
     * 
     * @param myCanvas The Canvas used in the GUI
     * @param inputTextbox The textbox used to type command
     */
    private void makePanels (Canvas myCanvas, JTextArea inputTextbox) {
        JPanel modulePanel = addSideBarPanel(controller, inputTextbox);
        JPanel inputPanel = addInputPanel(inputTextbox, controller);
        JPanel optionsPanel = addOptionsPanel(myCanvas, controller);
        addPanelsToLayout(myCanvas, modulePanel, inputPanel, optionsPanel);
    }

    /**
     * Creates a JMenu for the GUI
     * 
     * @param controller
     */
    private void addMenu (ViewController controller) {
        WorkSpacePreferences selector = new WorkSpacePreferences(controller);
        MenuBar menu = new MenuBar(controller);
        menu.add("selector", selector);
        setJMenuBar(menu);
    }

    /**
     * Creates an options panel for the GUI
     * 
     * @param myCanvas - canvas used in GUI
     * @param controller - controller used to send information to Model
     * @return an OptionsPanel
     */
    private JPanel addOptionsPanel (Canvas myCanvas, ViewController controller) {
        JPanel optionsPanel = new OptionsPanel(controller);
        return optionsPanel;
    }

    /**
     * Creates an options panel for the GUI
     * 
     * @param textbox - input textbox
     * @param controller - controller used to send information to Model
     * @return an InputPanel
     */
    private JPanel addInputPanel (JTextArea textbox, ViewController controller) {
        JPanel inputPanel = new InputPanel(textbox, controller, myModel);
        updatables.add((UpdatableDisplay) inputPanel);
        return inputPanel;
    }

    private JPanel addSideBarPanel (ViewController controller, JTextArea textbox) {
        JPanel sideBarPanel = new InputDisplayPanel(controller, myModel);
        updatables.add((UpdatableDisplay) sideBarPanel);
        return sideBarPanel;
    }

    /**
     * Initializes display settings of Slogo Frame
     * 
     */
    private void initializeDisplaySettings () {
        setTitle("SLogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(Constants.GUI_WIDTH, Constants.GUI_HEIGHT));
    }

    /**
     * Adds Panels to the appropriate positions
     * 
     * @param canvas the Canvas used in the GUI
     * @param sideBarPanel panel that displays history, commands, and variables
     * @param inputPanel panel that allows users to enter input
     * @param optionsPanel panel that allows users to modify options
     */
    private void addPanelsToLayout (Canvas canvas,
                                    JPanel sideBarPanel,
                                    JPanel inputPanel,
                                    JPanel optionsPanel) {
        this.getContentPane().add(sideBarPanel, BorderLayout.EAST);
        this.getContentPane().add(inputPanel, BorderLayout.SOUTH);
        this.getContentPane().add(optionsPanel, BorderLayout.NORTH);
        this.getContentPane().add(canvas, BorderLayout.CENTER);

    }

    /**
     * Notifies all objects that have an updatables interface
     * that a change was made on the GUI
     * 
     * @param error used to pass errors to the Canvas to display
     */
    public void notifyUpdatables (String error) {

        for (UpdatableDisplay updateable : updatables) {
            updateable.updateDisplay(error);
        }

    }

    /**
     * Used to change workspaces. When workspaces change, a different Model is used
     * 
     * @param model new model to be used in new workspae
     */
    public void changeCurrentModel (Model model) {

        for (UpdatableDisplay updateable : updatables) {
            updateable.changeModel(model);
        }

    }
}
