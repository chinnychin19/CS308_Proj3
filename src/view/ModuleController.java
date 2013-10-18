package view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import model.Model;
import view.modulePanel.ModuleData;


public class ModuleController {
    private Model currentModel;

    public ModuleController (Model model) {
        currentModel = model;
    }

    protected Map<String, Collection<ModuleData>> getModelInformation () {
        Map<String, Collection<ModuleData>> ret = new HashMap<String, Collection<ModuleData>>();
        ret.put("variable", getStoredVariables());
        return ret;
    }

    protected Collection<ModuleData> getStoredVariables () {
        Collection<ModuleData> variableCollection = new ArrayList<ModuleData>();
        Map<String, String> variableMap = currentModel.getAllVariables();
        for (String key : variableMap.keySet()) {

            variableCollection.add(new ModuleData(key, variableMap.get(key)));
        }
        return variableCollection;
    }

    protected Collection<ModuleData> getStoredHistory () {
        Collection<ModuleData> historyCollection = new ArrayList<ModuleData>();
        historyCollection.add(new ModuleData("test", "test2"));
        for (String history : currentModel.getHistory()) {
            historyCollection.add(new ModuleData(history, history));
        }
        return historyCollection;

    }

    protected Collection<ModuleData> getStoredCommand () {
        Collection<ModuleData> commandCollection = new ArrayList<ModuleData>();
        // commandCollection.add(new ModuleData("test", "test2"));
        Map<String, String> commandMap = currentModel.getAllCommands();
        for (String key : commandMap.keySet()) {
            commandCollection.add(new ModuleData(key, commandMap.get(key)));
        }
        return commandCollection;
    }

}
