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
        JButton run = new JButton("RUN");
        undo = new JButton("UNDO");

        redo = new JButton("REDO");
        undo.setEnabled(false);
        redo.setEnabled(false);
        run.addMouseListener(myController.addExecuteListener());
        undo.addMouseListener(myController.addUndoListener());
        redo.addMouseListener(myController.addRedoListener());
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
