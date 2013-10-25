package stack;

import java.util.HashMap;
import model.Turtle;


/**
 * 
 * Stack node class to be used by the undo and redo stacks in the model. Stores the map of turtles
 * at the current state as well as the next node in the stack. Essentially stores the current state
 * of turtles
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class TurtleStackNode {
    private HashMap<Integer, Turtle> myTurtles;
    private TurtleStackNode nextNode;

    /**
     * Constructor for the stack node that creates a new stack node with a given map of turtles.
     * Initializes the map of turtles to the map that is passed in and sets the next node to null
     * 
     * @param newTurtles The map of turtles to store in the node
     */
    public TurtleStackNode (HashMap<Integer, Turtle> newTurtles) {
        myTurtles = newTurtles;
        nextNode = null;
    }

    /**
     * @return The map of turtles in the node
     */
    public HashMap<Integer, Turtle> getTurtles () {
        return myTurtles;
    }

    /**
     * @return The next node in the stack
     */
    public TurtleStackNode getNext () {
        return nextNode;
    }

    /**
     * Sets the next node in the stack
     * 
     * @param next The node to set the next node of the current node to
     */
    public void setNext (TurtleStackNode next) {
        nextNode = next;
    }
}
