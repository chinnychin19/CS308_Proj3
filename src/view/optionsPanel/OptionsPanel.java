package view.optionsPanel;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class OptionsPanel extends JPanel {

    public OptionsPanel (JButton penColorChooser, JButton bgColorChooser,
                         JCheckBox statusCheckBox, JCheckBox gridCheckBox,
                         JComboBox<?> imageChooserButton, JButton helpMeButton) {
        super();
        this.setLayout(new GridLayout(1, 0));
        this.add(statusCheckBox);
        this.add(gridCheckBox);
        this.add(penColorChooser);
        this.add(bgColorChooser);
        this.add(imageChooserButton);
        this.add(helpMeButton);

    }

}
