package model;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * Command history class that keeps track of all commands that the user has entered. Stores these
 * commands in a list of strings
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class CommandHistory {
    private List<String> myHistory;

    /**
     * Constructor for the command history. Creates a new list to hold the user-entered commands
     */
    protected CommandHistory () {
        myHistory = new ArrayList<String>();
    }

    /**
     * Adds a command to the command history
     * 
     * @param command The command to add to the command history
     */
    protected void add (String command) {
        myHistory.add(command);
    }

    /**
     * @return The entire command history as a list of strings
     */
    protected List<String> getHistory () {
        return new ArrayList<String>(myHistory);
    }

    /**
     * Clears the command history
     */
    protected void clear () {
        myHistory.clear();
    }
}
