package model;

import java.util.HashMap;
import java.util.Map;
import model.command.Command;


public class CommandCache {
    // TODO
    private Map<String, Command> myMap;

    protected CommandCache () {
        myMap = new HashMap<String, Command>();
    }

    protected Command get (String key) {
        return myMap.get(key);
    }

    protected void put (String key, Command value) {
        myMap.put(key, value);
    }

    protected void clear () {
        myMap.clear();
    }

    public Map<String, String> getAllCommands () {
        Map<String, String> copy = new HashMap<String, String>();
        for (String key : myMap.keySet()) {
            String value = myMap.get(key).getBody();
            copy.put(key, value);
        }
        return copy;
    }

}
