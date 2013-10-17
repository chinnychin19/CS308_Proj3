package view;

import java.util.Map;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import view.inputPanel.InputPanel;
import view.inputPanel.RunButton;
import view.inputPanel.Textbox;
import view.modulePanel.ModulePanel;
import view.optionsPanel.OptionsPanel;


/**
 * Factory to create the different panels in the
 * GUI
 * 
 * @author lalitamaraj
 * 
 */
public class PanelFactory {
    public static JPanel makePanel (String panel, Map<String, JComponent> parameters) {
        if (panel.equals("module")) { return new ModulePanel((Textbox) parameters.get("textbox")); }
        if (panel.equals("input")) { return new InputPanel((Textbox) parameters.get("textbox"),
                                                           (RunButton) parameters.get("runbutton")); }
        if (panel.equals("option")) { return new OptionsPanel((JButton) parameters.get("pen"),
                                                              (JButton) parameters.get("bg"),
                                                              (JCheckBox) parameters.get("status"),
                                                              (JCheckBox) parameters.get("grid"),
                                                              (JComboBox<?>) parameters
                                                                      .get("image"));
        // /,(JButton) parameters.get("help"));

        }
        return null;

    }
}
