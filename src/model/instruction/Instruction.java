package model.instruction;

import java.util.ArrayList;
import java.util.List;


public abstract class Instruction {
    protected List<Instruction> myChildren;
    protected int myParamCount;

    public Instruction (int paramCount) {
        myParamCount = paramCount;
        myChildren = new ArrayList<Instruction>();
    }

    public List<Instruction> getChildren () {
        return myChildren;
    }

    public void addChild (Instruction child) {
        if (myChildren.size() >= myParamCount - 1) {
            try {
                throw new Exception("Too many parameters added to the instruction.");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        myChildren.add(child);
    }

    protected abstract Instruction eval ();
}
