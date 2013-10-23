package view.optionsPanel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import view.Constants;
import view.ViewController;


/**
 * Class that creates checkbox to toggle on/off for displaying of status of active turtle(s)
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 * 
 */
public class StatusCheckBox extends JCheckBox {
    private ViewController myController;

    /**
     * Constructor for HighlightCheckBox class
     * 
     * @param controller Controller used to send status of turtle status to Canvas
     */
    public StatusCheckBox (ViewController controller) {
        super(Constants.TURTLE_STATUS, null, true);
        myController = controller;

        addItemListener(new ItemListener() {

            public void itemStateChanged (ItemEvent e) {
                myController.setTurtleStatus(e.getStateChange() == ItemEvent.SELECTED);
            }

        });

    }

}
