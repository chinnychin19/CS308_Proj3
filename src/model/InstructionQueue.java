package model;

import java.util.LinkedList;
import java.util.Queue;
import dataType.DataType;
import model.instruction.Instruction;


public class InstructionQueue {
    private Queue<Instruction> myQueue;

    protected InstructionQueue () {
        // TODO
        myQueue = new LinkedList<Instruction>();
    }

    public DataType processNextInstruction () {
        // TODO
        return null;
    }

    public boolean hasNextInstruction () {
        return !myQueue.isEmpty();
    }
}
