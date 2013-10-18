package view.optionsPanel;

import java.awt.GridLayout;
import java.util.Collection;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import view.Observer;
import view.display.Canvas;
import view.modulePanel.ModuleData;


@SuppressWarnings("serial")
public class OptionsPanel extends JPanel implements Observer {
    private Canvas myCanvas;

    public OptionsPanel (JButton penColorChooser, JButton bgColorChooser,
                         JCheckBox statusCheckBox, JCheckBox gridCheckBox,
                         JComboBox<?> imageChooserButton) {

        super();
        this.setLayout(new GridLayout(1, 0));
        this.add(statusCheckBox);
        this.add(gridCheckBox);
        this.add(penColorChooser);
        this.add(bgColorChooser);
        this.add(imageChooserButton);

    }

    @Override
    public void update (String error,
                        String updateVariable,
                        Map<String, Collection<ModuleData>> moduleMap) {
        // TODO Auto-generated method stub

    }

}
