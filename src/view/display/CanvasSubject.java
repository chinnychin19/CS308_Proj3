package view.display;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Model;
import model.Path;
import view.Subject;


public class CanvasSubject implements Subject {
    List<CanvasObserver> observers;

    Model myCurrentModel;

    public CanvasSubject (Model model, Canvas myCanvas) {
        myCurrentModel = model;

        observers = new ArrayList<CanvasObserver>();
        addObservers(myCanvas);
    }

    @Override
    public void notifyObservers (String error) {
        ArrayList<Integer> activeTurtleList = getActiveTurtles();
        Map<Integer, Double> turtleXMap = getTurtleX();
        Map<Integer, Double> turtleYMap = getTurtleY();
        Map<Integer, Double> turtleAngleMap = getTurtleAngle();
        Map<Integer, Boolean> turtleVisibilityMap = getTurtleVisibility();
        Collection<Path> paths = myCurrentModel.getTurtlePaths();
        Color pen = myCurrentModel.getPenColor();
        Color bg = myCurrentModel.getBGColor();
        Boolean gridStatus = true; 
        Boolean turtleStatus = true;
        Integer penSize = myCurrentModel.getPenSize();

        for (CanvasObserver observer : observers) {
            observer.update(error, activeTurtleList, turtleXMap,
                            turtleYMap, turtleAngleMap, turtleVisibilityMap, paths, pen, bg,
                            gridStatus, turtleStatus, penSize);
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

    public void addObservers (Canvas myCanvas) {
        observers.add(myCanvas);

    }

    @Override
    public void changeCurrentModel (Model model) {
        myCurrentModel = model;

    }

}
