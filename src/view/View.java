package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.Model;
import view.sidebar.CommandsModule;
import view.sidebar.HistoryModule;
import view.sidebar.VariableModule;
import view.display.ViewUpdater;
import view.input.RunButton;
import view.input.Textbox;


public class View extends JFrame {
    private static final int GUI_WIDTH = 500;
    private static final int GUI_HEIGHT = 400;
    private static final int DISPLAY_HEIGHT = 100;
    private static final int DISPLAY_WIDTH = 30;
    protected ViewUpdater myViewUpdater;
    protected RunButton myRunButton;
    protected Textbox myTextbox;

    private static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    private static final String USER_DIR = "user.dir";
    private static final int FIELD_SIZE = 30;
    private ResourceBundle myResources;
    private ActionListener myActionListener;
    private KeyListener myKeyListener;
    private MouseListener myMouseListener;
    private MouseMotionListener myMouseMotionListener;
    private FocusListener myFocusListener;

    public View () {
        setTitle("SLogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(GUI_WIDTH, GUI_HEIGHT));

        final JPanel modulePanel = new JPanel();
        modulePanel.setLayout(new GridLayout(2, 2));
        modulePanel.add(new VariableModule(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        modulePanel.add(new HistoryModule(DISPLAY_WIDTH, DISPLAY_HEIGHT));
        modulePanel.add(new CommandsModule(DISPLAY_WIDTH, DISPLAY_HEIGHT));

        final JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1, 1));
        inputPanel.add(makeInput());

        final JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(1, 0));
        optionsPanel.add(makeButton());

        this.getContentPane().add(modulePanel, BorderLayout.NORTH);
        this.getContentPane().add(inputPanel, BorderLayout.CENTER);
        this.getContentPane().add(optionsPanel, BorderLayout.SOUTH);

        setVisible(true);

        Model.initModel();
    }

    private JComponent makeDisplay () {
        JPanel display = new Display(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        return display;
    }

    private JComponent makeModule () {
        VariableModule variable = new VariableModule(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        return variable;
    }

    private JButton makeButton () {
        JButton result = new JButton("RUN");
        return result;
    }

    protected JComponent makeInput () {
        JPanel result = new JPanel();
        result.add(makeTextField());
        result.add(makeButton());

        return result;
    }

    protected JTextField makeTextField () {
        JTextField result = new JTextField(FIELD_SIZE);
        result.addKeyListener(myKeyListener);
        result.addFocusListener(myFocusListener);
        result.addActionListener(myActionListener);
        return result;
    }

    protected void sendInput () {
        // when runButton is activated
        // input is sent to model
        String input = "";
        Model.parseInput(input);
        // Clear textbox
    }

    protected void executeInput () {
        while (Model.hasNextInstruction()) {
            Model.processNextInstruction();
            updateDisplay();
            updateSidebar();

        }

    }

    private void updateSidebar () {

    }

    private void updateDisplay () {
        myViewUpdater.displayOutput();
    }

    public static void main (String[] args) {
        new View();
    }
}
