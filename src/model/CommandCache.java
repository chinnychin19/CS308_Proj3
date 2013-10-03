package model;

import java.util.HashMap;
import java.util.Map;
import model.command.Command;

public class CommandCache {
    //TODO
    private Map<String, Command> myMap;
    protected CommandCache() {
        myMap = new HashMap<String, Command>();
    }
    
    public Command get(String key) {
        return myMap.get(key);
    }
    
    public void put(String key, Command value) {
        myMap.put(key, value);
    }
    
    //TODO: should this be protected? Does View need access?
    public void clear() {
        myMap.clear();
    }

}
