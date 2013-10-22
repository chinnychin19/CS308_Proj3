package view.optionsPanel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;

import view.ViewController;



public class GridCheckBox extends JCheckBox {

    private ViewController myController;


    public GridCheckBox (ViewController controller) {

        super("Grid", null, false);
        myController = controller;
        addItemListener(new ItemListener() {
            public void itemStateChanged (ItemEvent e) {
                myController.setGrid((e.getStateChange() == ItemEvent.SELECTED));
            }

        });
    }
}
