package view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import view.modulePanel.ModuleData;
import model.Model;

public class Subject {
    List<Observer> observers;
    Model myCurrentModel; 
    View myView;
    public Subject(Model model,View view){
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
        Map<String, Collection<ModuleData>> moduleMap = getModelInformation (myCurrentModel);
        for (Observer observer : observers){
            observer.update(error,updateVariable,moduleMap );
        }
        
    }
    private void getTurtleData(){
//        myCanvas.moveTurtle(myModel.getTurtleX(1), myModel.getTurtleY(1));
//        myCanvas.setHeading(myModel.getTurtleAngle(1));
//        myCanvas.setPaths(myModel.getTurtlePaths());
//        myCanvas.isTurtleVisible(myModel.isTurtleVisible(1));
    }
    
    protected void addObservers(Observer observer){
        observers.add(observer);
    }

}
