package view.optionsPanel;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import view.ViewController;


/**
 * Class that contains the Swing components of the view that allow toggling of canvas preferences
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 * 
 */
@SuppressWarnings("serial")
public class OptionsPanel extends JPanel {

    /**
     * Constructor for OptionsPanel class
     * 
     * @param controller Controller used to send background index selection to Model
     */
    public OptionsPanel (ViewController controller) {
        super();
        this.setLayout(new GridLayout(1, 0));
        this.add(new StatusCheckBox(controller));
        this.add(new GridCheckBox(controller));
        this.add(new HighlightCheckBox(controller));
        this.add(new ImageChooser(controller));
        this.add(new PenColorChooser(controller));
        this.add(new BackgroundColorChooser(controller));
    }

}
