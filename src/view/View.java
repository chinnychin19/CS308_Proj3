package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import view.display.ViewUpdater;
import view.input.RunButton;
import view.input.Textbox;
import view.sidebar.SideBar;


public class View extends JFrame {
    private static final int DISPLAY_HEIGHT = 100;
    private static final int DISPLAY_WIDTH = 3;
    protected ViewUpdater myViewUpdater;
    protected RunButton myRunButton;
    protected Textbox myTextbox;
    protected SideBar mySidebar;

    private static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    private static final String USER_DIR = "user.dir";
    private static final int FIELD_SIZE = 30;
    private JFileChooser myChooser;
    private ResourceBundle myResources;
    private ActionListener myActionListener;
    private KeyListener myKeyListener;
    private MouseListener myMouseListener;
    private MouseMotionListener myMouseMotionListener;
    private FocusListener myFocusListener;

    public View () {
        setTitle("SLogo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myChooser = new JFileChooser(System.getProperties().getProperty(USER_DIR));
        getContentPane().add(makeDisplay(), BorderLayout.NORTH);
        getContentPane().add(makeInput(), BorderLayout.SOUTH);

        getContentPane().add(makeSideBar(), BorderLayout.EAST);

        pack();
        setVisible(true);
        Model.initModel();
    }

    private JComponent makeSideBar () {
        return new SideBar(30, 100);
    }

    private JComponent makeDisplay () {
        JPanel display = new Display(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        return new JScrollPane(display);
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
        mySidebar.update();
    }

    private void updateDisplay () {
        myViewUpdater.displayOutput();
    }

    public static void main (String[] args) {
        new View();
    }
}
