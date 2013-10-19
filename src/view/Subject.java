package view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import view.modulePanel.ModuleData;
import model.Model;
import model.Path;


public class Subject {
    List<Observer> observers;
    Model myCurrentModel;
    View myView;

    public Subject (Model model, View view) {
        myCurrentModel = model;
        myView = view;
        observers = new ArrayList<Observer>();
    }

    public Map<String, Collection<ModuleData>> getModelInformation (Model currentModel) {
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

    public void notifyObservers (String error, String updateVariable) {
        Map<String, Collection<ModuleData>> moduleMap = getModelInformation(myCurrentModel);
        ArrayList<Integer> activeTurtleList = getActiveTurtles();
        Map<Integer, Double> turtleXMap = getTurtleX();
        Map<Integer, Double> turtleYMap = getTurtleY();
        Map<Integer, Double> turtleAngleMap = getTurtleAngle();
        Map<Integer, Boolean> turtleVisibilityMap = getTurtleVisibility();
        Collection<Path> paths = myCurrentModel.getTurtlePaths();
        for (Observer observer : observers) {
            observer.update(error, updateVariable, moduleMap, activeTurtleList, turtleXMap,
                            turtleYMap, turtleAngleMap, turtleVisibilityMap, paths);
        }

    }

    private ArrayList<Integer> getActiveTurtles () {

        ArrayList<Integer> activeTurtleList = new ArrayList<Integer>(); // myModel.getActiveTurtleIDs();
        activeTurtleList.add(1);
        return activeTurtleList;
    }

    private Map<Integer, Double> getTurtleX () {
        Map<Integer, Double> turtleXMap = new HashMap<Integer, Double>();
        ArrayList<Integer> activeTurtleList = getActiveTurtles();
        for (Integer ID : activeTurtleList) {
            turtleXMap.put(ID, myCurrentModel.getTurtleX(ID));
        }

        return turtleXMap;

    }

    private Map<Integer, Double> getTurtleY () {
        Map<Integer, Double> turtleYMap = new HashMap<Integer, Double>();
        ArrayList<Integer> activeTurtleList = getActiveTurtles();
        for (Integer ID : activeTurtleList) {
            turtleYMap.put(ID, myCurrentModel.getTurtleY(ID));
        }

        return turtleYMap;

    }

    private Map<Integer, Double> getTurtleAngle () {
        Map<Integer, Double> turtleAngleMap = new HashMap<Integer, Double>();
        ArrayList<Integer> activeTurtleList = getActiveTurtles();
        for (Integer ID : activeTurtleList) {
            turtleAngleMap.put(ID, myCurrentModel.getTurtleAngle(ID));
        }

        return turtleAngleMap;

    }

    private Map<Integer, Boolean> getTurtleVisibility () {
        Map<Integer, Boolean> turtleVisibilityMap = new HashMap<Integer, Boolean>();
        ArrayList<Integer> activeTurtleList = getActiveTurtles();
        for (Integer ID : activeTurtleList) {
            turtleVisibilityMap.put(ID, myCurrentModel.isTurtleVisible(ID));
        }

        return turtleVisibilityMap;

    }

    protected void addObservers (Observer observer) {
        observers.add(observer);
    }

    public void changeCurrentModel (Model model) {
        myCurrentModel = model;
    }

}
