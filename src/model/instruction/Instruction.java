package model.instruction;

import java.util.ArrayList;
import java.util.List;
import model.Model;
import model.instruction.loop.InstructionLoop;
import model.instruction.error.*;
import model.instruction.error.InternalError;


public abstract class Instruction {
    private List<Instruction> myChildren;
    private int myParamCount;
    private Instruction myParent;
    private Model myModel;

    public Instruction (int paramCount, Instruction parent, Model m) {
        myParamCount = paramCount;
        myChildren = new ArrayList<Instruction>();
        myParent = parent;
        myModel = m;
    }

    public Model getModel () {
        return myModel;
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
        myChildren.add(child);
    }

    public abstract Instruction eval () throws Exception;
}
