package view.optionsPanel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import view.View;
import view.display.Canvas;


public class GridCheckBox extends JCheckBox {

    private OptionsPanelController myController;

    public GridCheckBox (OptionsPanelController controller) {

        super("Grid", null, false);
        myController = controller;
        addItemListener(new ItemListener() {

            public void itemStateChanged (ItemEvent e) {

                myController.setGrid((e.getStateChange() == ItemEvent.SELECTED));

            }

        });
    }
}
