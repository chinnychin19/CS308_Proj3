package stack;

import java.util.HashMap;
import java.util.Map;
import model.Turtle;


public class Stack {
    private StackNode head;

    public Stack () {
        head = null;
    }

    public void push (HashMap<Integer, Turtle> myTurtles) {
        HashMap<Integer, Turtle> newMap = new HashMap<Integer, Turtle>();
        for (int ID : myTurtles.keySet()) {
            newMap.put(ID, myTurtles.get(ID).clone());
        }
        StackNode nextNode = new StackNode(newMap);
        nextNode.setNext(head);
        head = nextNode;
    }

    public HashMap<Integer, Turtle> pop () {
        HashMap<Integer, Turtle> ret = head.getTurtles();
        head = head.getNext();
        return ret;
    }

    public boolean isEmpty () {
        return head == null ? true : false;
    }

    public void clear () {
        head = null;
    }
}
