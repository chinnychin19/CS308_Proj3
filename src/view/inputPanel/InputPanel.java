package view.inputPanel;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import model.Model;
import view.Constants;
import view.UpdatableDisplay;
import view.ViewController;


/**
 * JPanel that contains the elements that the user can interact with
 * to send input to the Model. To illustrate, the textbox is contained in this panel.
 * The textbox allows the user to type commands to be sent to the model
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 * 
 */
@SuppressWarnings("serial")
public class InputPanel extends JPanel implements UpdatableDisplay {
    private ViewController myController;
    private JButton undo;
    private JButton redo;
    private Model myCurrentModel;

    /**
     * Constructor
     * 
     * @param jTextArea Input textbox
     * @param controller Controller used to send information to Model
     * @param model Model used to gather information
     * */
    public InputPanel (JTextArea jTextArea, ViewController controller, Model model) {
        super();
        this.setLayout(new GridLayout(Constants.INPUT_PANEL_ROWS, Constants.INPUT_PANEL_COLUMNS));
        this.add(new JScrollPane(jTextArea));
        myCurrentModel = model;
        myController = controller;
        addButtons();
    }

    /**
     * Helper method to add the buttons to the InputPanel
     */
    private void addButtons () {
        JButton run = new RunButton(myController);
        undo = new UndoButton(myController);
        redo = new RedoButton(myController);

        undo.setEnabled(false);
        redo.setEnabled(false);

        this.add(run);
        this.add(undo);
        this.add(redo);
    }

    /**
     * Communicates with model to determine if a user can select undo
     * 
     * @return boolean to indicate if undo is possible
     */
    private Boolean canUndo () {
        return myCurrentModel.canUndo();
    }

    /**
     * Communicates with model to determine if a user can select redo
     * 
     * @return boolean to indicate if redo is possible
     */
    private Boolean canRedo () {
        return myCurrentModel.canRedo();

    }

    @Override
    public void updateDisplay (String error) {
        undo.setEnabled(canUndo());
        redo.setEnabled(canRedo());

    }

    @Override
    public void changeModel (Model model) {
        myCurrentModel = model;

    }

}
