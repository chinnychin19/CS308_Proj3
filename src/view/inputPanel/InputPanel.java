package view.inputPanel;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class InputPanel extends JPanel {
    private InputController myController;

    public InputPanel (JTextArea jTextArea, InputController controller) {
        super();
        this.setLayout(new GridLayout(1, 4));

        this.add(new JScrollPane(jTextArea));
        myController = controller;
        JButton run = new JButton("RUN");
        JButton undo = new JButton("UNDO");

        JButton redo = new JButton("REDO");

        run.addMouseListener(myController.addExecuteListener());
        undo.addMouseListener(myController.addUndoListener());
        redo.addMouseListener(myController.addRedoListener());
        this.add(run);
        this.add(undo);
        this.add(redo);

    }

}
