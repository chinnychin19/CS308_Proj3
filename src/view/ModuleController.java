package view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import model.Model;
import view.modulePanel.ModuleData;


public class ModuleController {

    public ModuleController () {

    }

    protected Map<String, Collection<ModuleData>> getModelInformation (Model currentModel) {
        Map<String, Collection<ModuleData>> ret = new HashMap<String, Collection<ModuleData>>();
        ret.put("variable", getStoredVariables(currentModel));
        ret.put("history", getStoredHistory(currentModel));
        ret.put("command", getStoredCommand(currentModel));
        return ret;
    }

    protected Collection<ModuleData> getStoredVariables (Model currentModel) {
        Collection<ModuleData> variableCollection = new ArrayList<ModuleData>();
        Map<String, String> variableMap = currentModel.getAllVariables();
        for (String key : variableMap.keySet()) {

            variableCollection.add(new ModuleData(key, key));
        }
        return variableCollection;
    }

    protected Collection<ModuleData> getStoredHistory (Model currentModel) {
        Collection<ModuleData> historyCollection = new ArrayList<ModuleData>();

        for (String history : currentModel.getHistory()) {
            historyCollection.add(new ModuleData(history, history));
        }
        return historyCollection;

    }

    protected Collection<ModuleData> getStoredCommand (Model currentModel) {
        Collection<ModuleData> commandCollection = new ArrayList<ModuleData>();

        Map<String, String> commandMap = currentModel.getAllCommands();
        for (String key : commandMap.keySet()) {
            commandCollection.add(new ModuleData(key, commandMap.get(key)));
        }
        return commandCollection;
    }

}
