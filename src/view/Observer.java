package view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import model.Path;
import view.modulePanel.ModuleData;


public interface Observer {

    public void update (String error,
                        String updateVariable,
                        Map<String, Collection<ModuleData>> moduleMap,
                        ArrayList<Integer> activeTurtleList,
                        Map<Integer, Double> turtleXMap,
                        Map<Integer, Double> turtleYMap,
                        Map<Integer, Double> turtleAngleMap,
                        Map<Integer, Boolean> turtleVisibilityMap,
                        Collection<Path> paths, Color bgColor);

}
