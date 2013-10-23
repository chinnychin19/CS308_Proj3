package stack;

import java.util.HashMap;
import java.util.Map;
import model.Turtle;


public class StackNode {
    private HashMap<Integer, Turtle> myTurtles;
    private StackNode nextNode;

    public StackNode (HashMap<Integer, Turtle> newTurtles) {
        myTurtles = newTurtles;
        nextNode = null;
    }

    public HashMap<Integer, Turtle> getTurtles () {
        return myTurtles;
    }

    public StackNode getNext () {
        return nextNode;
    }

    public void setNext (StackNode next) {
        nextNode = next;
    }
}
