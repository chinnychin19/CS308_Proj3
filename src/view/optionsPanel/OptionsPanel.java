package view.optionsPanel;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import view.ViewController;
import view.display.Canvas;


@SuppressWarnings("serial")
public class OptionsPanel extends JPanel {
    private Canvas myCanvas;

    public OptionsPanel (ViewController controller) {

        super();
        this.setLayout(new GridLayout(1, 0));
        this.add(new StatusCheckBox(controller));
        this.add(new GridCheckBox(controller));
        this.add(new HighlightCheckBox(controller));
        this.add(new PenColorChooser(controller));
        this.add(new BackgroundColorChooser(controller));
        // this.add(imageChooserButton);

    }

}
