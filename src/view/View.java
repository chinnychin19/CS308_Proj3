package view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import model.Model;
import view.display.ViewUpdater;
import view.input.RunButton;
import view.input.Textbox;
import view.sidebar.SideBar;


public class View extends JFrame {
    protected ViewUpdater myViewUpdater;
    protected RunButton myRunButton;
    protected Textbox myTextbox;
    protected SideBar mySidebar;
    protected Model myModel;
    
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    private static final String USER_DIR = "user.dir";
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
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
        

        pack();
        setVisible(true);
    }
    
    private JButton makeButton (){
        JButton result = new JButton("RUN");
        return result;
    }

    protected void sendInput () {
        // when runButton is activated
        // input is sent to model
        String input = "";
        myModel.parseInput(input);
        // Clear textbox
    }

    protected void executeInput () {
        while (myModel.hasNextInstruction()) {
            myModel.processNextInstruction();
            
            while (myModel.hasNextTurtleMove()){
                myModel.processNextTurtleMove();
                updateDisplay();
                updateSidebar();
            }
        }

    }

    private void updateSidebar () {
        mySidebar.update();
    }

    private void updateDisplay () {
        myViewUpdater.displayOutput();
    }
    
    public static void main (String[] args){
        new View();
    }
}
