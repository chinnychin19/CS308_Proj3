package view.optionsPanel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import view.View;
import view.ViewController;
import view.display.Canvas;


public class StatusCheckBox extends JCheckBox {
    private ViewController myController;

    public StatusCheckBox (ViewController controller) {
        super("Turtle Status", null, true);
        myController = controller;

        addItemListener(new ItemListener() {

            public void itemStateChanged (ItemEvent e) {
                myController.setTurtleStatus(e.getStateChange() == ItemEvent.SELECTED);
            }

        });

    }

}
