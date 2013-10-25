package view.optionsPanel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import view.Constants;
import view.ViewController;


/**
 * Class that creates checkbox to toggle on/off status of grid
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 * 
 */
public class GridCheckBox extends JCheckBox {
    private ViewController myController;

    /**
     * Constructor for GridCheckBox class
     * 
     * @param controller Controller used to send status of grid to Canvas
     */
    public GridCheckBox (ViewController controller) {
        super(Constants.GRID, null, false);
        myController = controller;
        addItemListener(new ItemListener() {
            public void itemStateChanged (ItemEvent e) {
                myController.setGrid((e.getStateChange() == ItemEvent.SELECTED));
            }

        });
    }
}
