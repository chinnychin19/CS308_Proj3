package view.modulePanel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import view.Subject;
import model.Model;


public class ModuleSubject implements Subject {
    List<ModuleObserver> observers;
    Model myCurrentModel;

    public ModuleSubject (Model model) {
        myCurrentModel = model;

        observers = new ArrayList<ModuleObserver>();
    }

    public void addObservers (ModuleObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers () {
        Map<String, Collection<ModuleData>> moduleMap = getModelInformation(myCurrentModel);

        for (ModuleObserver observer : observers) {
            observer.update(moduleMap);
        }

    }

    private Map<String, Collection<ModuleData>> getModelInformation (Model currentModel) {
        Map<String, Collection<ModuleData>> ret = new HashMap<String, Collection<ModuleData>>();
        ret.put("variable", getStoredVariables(currentModel));
        ret.put("history", getStoredHistory(currentModel));
        ret.put("command", getStoredCommand(currentModel));
        return ret;
    }

    private Collection<ModuleData> getStoredVariables (Model currentModel) {
        Collection<ModuleData> variableCollection = new ArrayList<ModuleData>();
        Map<String, String> variableMap = currentModel.getAllVariables();
        for (String key : variableMap.keySet()) {

            variableCollection.add(new ModuleData(key, key));
        }
        return variableCollection;
    }

    private Collection<ModuleData> getStoredHistory (Model currentModel) {
        Collection<ModuleData> historyCollection = new ArrayList<ModuleData>();

        for (String history : currentModel.getHistory()) {
            historyCollection.add(new ModuleData(history, history));
        }
        return historyCollection;

    }

    private Collection<ModuleData> getStoredCommand (Model currentModel) {
        Collection<ModuleData> commandCollection = new ArrayList<ModuleData>();

        Map<String, String> commandMap = currentModel.getAllCommands();
        for (String key : commandMap.keySet()) {
            commandCollection.add(new ModuleData(key, commandMap.get(key)));
        }
        return commandCollection;
    }

    @Override
    public void changeCurrentModel (Model model) {
        myCurrentModel = model;

    }
}
