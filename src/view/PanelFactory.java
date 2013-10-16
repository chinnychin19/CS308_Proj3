package view;

import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JPanel;
import view.inputPanel.InputPanel;
import view.inputPanel.RunButton;
import view.inputPanel.Textbox;
import view.modulePanel.ModulePanel;


public class PanelFactory {
    public static JPanel makePanel (String panel, Map<String, JComponent> parameters) {
        if (panel.equals("module")) { return new ModulePanel((Textbox) parameters.get("textbox")); }
        if (panel.equals("input")) { return new InputPanel((Textbox) parameters.get("textbox"),
                                                           (RunButton) parameters.get("runbutton")); }
        return null;

    }
}
