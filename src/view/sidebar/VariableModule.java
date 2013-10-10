package view.sidebar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.swing.JLabel;
import model.Model;
import view.input.Textbox;


@SuppressWarnings("serial")
public class VariableModule extends Module {

    public VariableModule (int width, int height, Textbox textbox) {
        super(width, height, textbox);
        this.add(new JLabel("Variable Module"));
    }

    public VariableModule (Textbox textbox) {
        super(textbox);
        this.add(new JLabel("Variable Module"));
    }

    @Override
    protected Collection<ModuleData> getStoredModelInformation () {
        Collection<ModuleData> variableCollection = new ArrayList<ModuleData>();
        Map<String, String> variableMap = Model.getAllVariables();
        for (String key : variableMap.keySet()) {
            variableCollection.add(new ModuleData(key, variableMap.get(key)));
        }
        return variableCollection;
    }

}
