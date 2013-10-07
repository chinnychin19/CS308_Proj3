package view;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import view.display.Canvas;
import view.display.Display;
import view.display.ViewUpdater;
import view.input.RunButton;
import view.input.Textbox;


public class View extends JFrame {
    
    
    protected ViewUpdater myViewUpdater;
    protected RunButton myRunButton;
    protected Textbox myTextbox;

   
    private ResourceBundle myResources;
    private ActionListener myActionListener;
    private KeyListener myKeyListener;
    private MouseListener myMouseListener;
    private MouseMotionListener myMouseMotionListener;
    private FocusListener myFocusListener;

    public View () {

        setTitle("SLogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(Constants.GUI_WIDTH, Constants.GUI_HEIGHT));

        final JPanel modulePanel = new JPanel();
        modulePanel.setLayout(new GridLayout(3, 1));
        modulePanel.add(new VariableModule(Constants.DISPLAY_WIDTH, Constants.DISPLAY_HEIGHT));
        modulePanel.add(new HistoryModule(Constants.DISPLAY_WIDTH, Constants.DISPLAY_HEIGHT));
        modulePanel.add(new CommandsModule(Constants.DISPLAY_WIDTH, Constants.DISPLAY_HEIGHT));

        final JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1, 1));
        inputPanel.add(makeInput());

        final JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(1, 0));
        optionsPanel.add(new Checkbox("Grid", null, true));

        final JComboBox backgroundChooser = new JComboBox();
        backgroundChooser.addItem("COLOR");
        optionsPanel.add(backgroundChooser);

        optionsPanel.add(new Checkbox("Pen Down", null, true));

        optionsPanel.add(makeImageChooserButton());
        optionsPanel.add(makeHelpButton());
        // initEngineComponent will embed it
        this.getContentPane().add(modulePanel, BorderLayout.EAST);
        this.getContentPane().add(inputPanel, BorderLayout.SOUTH);
        this.getContentPane().add(optionsPanel, BorderLayout.NORTH);
        this.getContentPane().add(new Canvas(), BorderLayout.CENTER);

        setVisible(true);

        Model.initModel();
    }

    private JComponent makeImageChooserButton () {
        JButton result = new JButton("Image");
        return result;
    }

    private JComponent makeHelpButton () {
        JButton result = new JButton("Help Me");
        return result;
    }

    private JComponent makeDisplay () {
        JPanel display = new Display(Constants.DISPLAY_WIDTH, Constants.DISPLAY_HEIGHT);
        return display;
    }

    private JComponent makeModule () {
        VariableModule variable = new VariableModule(Constants.DISPLAY_WIDTH, Constants.DISPLAY_HEIGHT);
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
        JTextField result = new JTextField(Constants.FIELD_SIZE);
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
