package view.display;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import model.Path;


public interface CanvasObserver {

    public void update (String error, ArrayList<Integer> activeTurtleList,
                        Map<Integer, Double> turtleXMap,
                        Map<Integer, Double> turtleYMap,
                        Map<Integer, Double> turtleAngleMap,
                        Map<Integer, Boolean> turtleVisibilityMap,
                        Collection<Path> paths,
                        Color penColor,
                        Color bgColor,
                        Boolean gridStatus,
                        Boolean turtleStatus,
                        Integer penSize);

}