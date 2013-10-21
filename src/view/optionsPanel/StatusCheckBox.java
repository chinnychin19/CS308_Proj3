package view.optionsPanel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import view.View;
import view.display.Canvas;


public class StatusCheckBox extends JCheckBox {
    private OptionsPanelController myController;

    public StatusCheckBox (OptionsPanelController controller) {
        super("Turtle Status", null, true);
        myController = controller;

        addItemListener(new ItemListener() {

            public void itemStateChanged (ItemEvent e) {
                myController.setTurtleStatus(e.getStateChange() == ItemEvent.SELECTED);
            }

        });

    }

}
