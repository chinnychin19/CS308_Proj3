package view;

import java.util.Map;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import view.inputPanel.InputController;
import view.inputPanel.InputPanel;
import view.inputPanel.Textbox;
import view.modulePanel.ModulePanel;
import view.optionsPanel.OptionsPanel;
import view.optionsPanel.OptionsPanelController;


/**
 * Factory to create the different panels in the
 * GUI
 * 
 * @author lalitamaraj
 * 
 */
public class PanelFactory {
    public static JPanel makePanel (String panel,
                                    Map<String, JComponent> parameters,
                                    Controller controller) {
        if (panel.equals("module")) { return new ModulePanel((Textbox) parameters.get("textbox"),
                                                             controller); }
        if (panel.equals("input")) { return new InputPanel((Textbox) parameters.get("textbox"),
                                                           (InputController) controller); }
        if (panel.equals("option")) { return new OptionsPanel((OptionsPanelController) controller);
        // /,(JButton) parameters.get("help"));

        }
        return null;

    }
}
