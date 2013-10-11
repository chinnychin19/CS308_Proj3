package model;

import java.util.LinkedList;
import java.util.Queue;
import model.instruction.Instruction;


public class InstructionQueue {
    private Queue<Instruction> myQueue;

    protected InstructionQueue () {
        myQueue = new LinkedList<Instruction>();
    }

    protected String processNextInstruction () {
        try {
            return "" + myQueue.poll().eval();            
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    protected void add (Instruction inst) {
        myQueue.add(inst);
    }

    protected boolean hasNextInstruction () {
        return !myQueue.isEmpty();
    }
}
