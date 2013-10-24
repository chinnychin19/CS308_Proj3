package view.optionsPanel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import view.Constants;
import view.ViewController;


/**
 * Class that creates checkbox to toggle on/off highlighting of active turtle(s)
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 * 
 */
public class HighlightCheckBox extends JCheckBox {
    private ViewController myController;

    /**
     * Constructor for HighlightCheckBox class
     * 
     * @param controller Controller used to send status of highlighting to Canvas
     */
    public HighlightCheckBox (ViewController controller) {
        super(Constants.HIGHLIGHT, null, false);
        myController = controller;

        addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged (ItemEvent e) {
                myController.setHighlights((e.getStateChange() == ItemEvent.SELECTED));

            }

        });
    }

}
