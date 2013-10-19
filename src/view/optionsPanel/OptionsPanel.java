package view.optionsPanel;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import view.display.Canvas;


@SuppressWarnings("serial")
public class OptionsPanel extends JPanel {
    private Canvas myCanvas;

    public OptionsPanel (OptionsPanelController controller) {

        super();
        this.setLayout(new GridLayout(1, 0));
        // this.add(new statusCheckBox());
        // this.add(gridCheckBox);
         this.add(new PenColorChooser(controller));
        this.add(new BackgroundColorChooser(controller));
        // this.add(imageChooserButton);

    }

}
