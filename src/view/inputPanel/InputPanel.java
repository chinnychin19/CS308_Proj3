package view.inputPanel;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import model.Model;
import view.Updatable;
import view.ViewController;


@SuppressWarnings("serial")
public class InputPanel extends JPanel implements Updatable {
    private ViewController myController;
    private JButton undo;
    private JButton redo;
    private Model myCurrentModel;

    public InputPanel (JTextArea jTextArea, ViewController controller,Model model) {
        super();
        this.setLayout(new GridLayout(1, 4));

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
     * Speaks to model to determine if a user can select undo
     * 
     * @return boolean to indicate if undo is possible
     */
    private Boolean canUndo () {
        return myCurrentModel.canUndo();
    }

    /**
     * Speaks to model to determine if a user can select redo
     * 
     * @return boolean to indicate if redo is possible
     */
    private Boolean canRedo () {
        return myCurrentModel.canRedo();

    }

    @Override
    public void update () {
        undo.setEnabled(canUndo () );
        redo.setEnabled( canRedo ());
        
    }

}
