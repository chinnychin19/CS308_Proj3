package model.instruction;

import java.util.ArrayList;
import java.util.List;


public abstract class Instruction {
    protected List<Instruction> myChildren;
    private int myParamCount;
    protected Instruction myParent;

    public Instruction (int paramCount, Instruction parent) {
        myParamCount = paramCount;
        myChildren = new ArrayList<Instruction>();
        myParent = parent;
    }

    public int getNumParams () {
        return myParamCount;
    }

    public Instruction getParent () {
        return myParent;
    }

    public List<Instruction> getChildren () {
        return myChildren;
    }

    public void addChild (Instruction child) {
        if (myChildren.size() >= getNumParams()) {
            try {
                throw new Exception("Too many parameters added to the instruction.");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        myChildren.add(child);
    }

    public abstract Instruction eval ();
}
