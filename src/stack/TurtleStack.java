package stack;

import java.util.HashMap;
import model.Turtle;


/**
 * 
 * Stack that keeps track of the turtle states for undo and redo. Stores the current map of turtles
 * as an entry in the stack. Is used by both the undo and redo stacks to keep track of turtle
 * states. Stores a node as the head which is the last command added to the stack. Has push and pop
 * methods that follows normal stack conventions of LIFO
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class TurtleStack {
    private TurtleStackNode head;

    /**
     * Constructor for a turtle stack that creates a new turtle stack. Initializes the head node to
     * null
     */
    public TurtleStack () {
        head = null;
    }

    /**
     * Puts a new turtle state onto the stack. Puts the new state at the head of the stack and
     * initializes the original head as the next node in the stack
     * 
     * @param myTurtles The map of turtles at the current state
     */
    public void push (HashMap<Integer, Turtle> myTurtles) {
        HashMap<Integer, Turtle> newMap = new HashMap<Integer, Turtle>();
        for (int ID : myTurtles.keySet()) {
            newMap.put(ID, myTurtles.get(ID).clone());
        }
        TurtleStackNode nextNode = new TurtleStackNode(newMap);
        nextNode.setNext(head);
        head = nextNode;
    }

    /**
     * @return The turtle state last added to the stack and makes the new head of the stack the next
     *         node
     */
    public HashMap<Integer, Turtle> pop () {
        HashMap<Integer, Turtle> ret = head.getTurtles();
        head = head.getNext();
        return ret;
    }

    /**
     * @return True if the stack is empty, false if the stack is not empty
     */
    public boolean isEmpty () {
        return head == null ? true : false;
    }

    /**
     * Clears the stack
     */
    public void clear () {
        head = null;
    }
}
