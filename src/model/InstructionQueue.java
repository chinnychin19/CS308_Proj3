package model;

import java.util.LinkedList;
import java.util.Queue;
import dataType.DataType;
import model.instruction.Instruction;


public class InstructionQueue {
    private Queue<Instruction> myQueue;

    protected InstructionQueue () {
        myQueue = new LinkedList<Instruction>();
    }

    protected String processNextInstruction () {
        String s = "" + myQueue.poll().eval();
        return s;
    }
    
    protected void add(Instruction inst) {
        myQueue.add(inst);
    }

    protected boolean hasNextInstruction () {
        return !myQueue.isEmpty();
    }
}
