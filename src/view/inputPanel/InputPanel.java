package view.inputPanel;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class InputPanel extends JPanel implements InputObserver {
    private InputController myController;
    private JButton undo;
    private JButton redo;

    public InputPanel (JTextArea jTextArea, InputController controller) {
        super();
        this.setLayout(new GridLayout(1, 4));

        this.add(new JScrollPane(jTextArea));
        myController = controller;
        addButtons();
    }


    /**
     * Helper method to add the buttons to the InputPanel
     */
    private void addButtons () {
        JButton run = new RunButton(myController);
        JButton undo = new UndoButton(myController);
        JButton redo = new RedoButton(myController);

        undo.setEnabled(false);
        redo.setEnabled(false);

        this.add(run);
        this.add(undo);
        this.add(redo);
    }


    @Override
    public void update (Boolean canUndo, Boolean canRedo) {
        undo.setEnabled(canUndo);
        redo.setEnabled(canRedo);

    }

}
