package view.sidebar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import model.Model;
import view.input.Textbox;


@SuppressWarnings("serial")
public class VariableModule extends Module {
    private EditBar editBar;

    public VariableModule (int width, int height, Textbox textbox) {
        super(width, height, textbox);
        editBar = new EditBar(list, listModel);
        this.add(editBar);

    }

    public VariableModule (Textbox textbox) {
        super(textbox);
        editBar = new EditBar(list, listModel);
        this.add(editBar);
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

    @Override
    protected String getModuleName () {
        return "Variable Module";
    }

}
