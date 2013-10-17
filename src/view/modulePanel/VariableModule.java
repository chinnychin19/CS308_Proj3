package view.modulePanel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import model.Model;
import view.inputPanel.Textbox;


@SuppressWarnings("serial")
public class VariableModule extends Module {
    private EditBar myEditBar;

    public VariableModule (int width, int height, Textbox textbox) {
        super(width, height, textbox);
        myEditBar = new EditBar(myList, myListModel);
        this.add(myEditBar);

    }

    public VariableModule (Textbox textbox) {
        super(textbox);
        myEditBar = new EditBar(myList, myListModel);
        this.add(myEditBar);
    }

    @Override
    protected Collection<ModuleData> getStoredModelInformation () {
        Collection<ModuleData> variableCollection = new ArrayList<ModuleData>();
//        Map<String, String> variableMap = Model.getAllVariables();
//        for (String key : variableMap.keySet()) {
//
//            variableCollection.add(new ModuleData(key, variableMap.get(key)));
//        }
        return variableCollection;
    }

    @Override
    protected String getModuleName () {
        return "Variable Module";
    }

}
