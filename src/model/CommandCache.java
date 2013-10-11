package model;

import java.util.HashMap;
import java.util.Map;
import model.instruction.InstructionListNode;
import model.instruction.command.UserCommand;


public class CommandCache {
    private Map<String, UserCommand> myMap;

    protected CommandCache () {
        myMap = new HashMap<String, UserCommand>();
    }

    public UserCommand get (String key) {
        if (!myMap.containsKey(key)) { return null; }
        // delete previous command parameters before returning, but maintain root command node
        UserCommand ret = myMap.get(key);
        ret.getChildren().clear();
        ret.addChild(ret.getRootNode());
        return ret;
    }

    public boolean contains (String name) {
        return myMap.containsKey(name);
    }

    public void put (String name, UserCommand command) {
        myMap.put(name, command);
    }

    protected void clear () {
        myMap.clear();
    }

    protected Map<String, String> getAllCommands () {
        Map<String, String> copy = new HashMap<String, String>();
        for (String key : myMap.keySet()) {
            String value = myMap.get(key).getBody();
            copy.put(key, value);
        }
        return copy;
    }

}
