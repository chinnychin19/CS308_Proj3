package view.optionsPanel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;


public class HighlightCheckBox extends JCheckBox {
    private OptionsPanelController myController;

    public HighlightCheckBox (OptionsPanelController controller) {
        super("Highlight", null, false);
        myController = controller;

        addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged (ItemEvent e) {
                myController.setHighlights((e.getStateChange() == ItemEvent.SELECTED));

            }

        });
    }

}
