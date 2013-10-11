package model.instruction;

import java.util.ArrayList;
import java.util.List;
import model.instruction.loop.InstructionLoop;
import model.instruction.error.*;
import model.instruction.error.InternalError;


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
                throw new InternalError();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        myChildren.add(child);
    }

    public abstract Instruction eval () throws Exception;
}
