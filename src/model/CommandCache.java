package model;

import java.util.HashMap;
import java.util.Map;
import model.instruction.command.UserCommand;


/**
 * 
 * Command cache class that keeps track of user-defined commands. Utilizes a map with a string
 * identifying the command as the key and a UserCommand object as the value
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class CommandCache {
    private Map<String, UserCommand> myMap;

    /**
     * Constructor for the command cache. Creates a new map to hold the user-defined commands
     */
    protected CommandCache () {
        myMap = new HashMap<String, UserCommand>();
    }

    /**
     * Gets the user-defined command that pertains to the given command name
     * 
     * @param commandName Name of the user-defined command to retrieve
     * @return The user-defined command that pertains to the given command name
     */
    public UserCommand get (String commandName) {
        commandName = commandName.toUpperCase();
        if (!myMap.containsKey(commandName)) { return null; }
        // delete previous command parameters before returning, but maintain root command node
        UserCommand ret = myMap.get(commandName).copy();
        ret.getChildren().clear();
        ret.addChild(ret.getRootNode());
        // TODO: should return a COPY() of the command. VERY IMPORTANT
        return ret;
    }

    /**
     * Sees if the command name given has been defined in the command cache
     * 
     * @param commandName Name of the command to check if in command cache
     * @return True if the command is in the command cache, false if it is not
     */
    public boolean contains (String commandName) {
        commandName = commandName.toUpperCase();
        return myMap.containsKey(commandName);
    }

    /**
     * Stores a command in the command cache
     * 
     * @param commandName Name of the command to store
     * @param command The actual command that the user wants to store
     */
    public void put (String commandName, UserCommand command) {
        commandName = commandName.toUpperCase();
        myMap.put(commandName, command);
    }

    /**
     * Clears the command cache
     */
    protected void clear () {
        myMap.clear();
    }

    /**
     * @return The entire map of user-defined commands in the command cache
     */
    protected Map<String, String> getAllCommands () {
        Map<String, String> copy = new HashMap<String, String>();
        for (String commandName : myMap.keySet()) {
            String value = myMap.get(commandName).getBody();
            copy.put(commandName, value);
        }
        return copy;
    }

}
