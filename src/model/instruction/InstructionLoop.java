package model.instruction;

import model.Model;


public abstract class InstructionLoop extends Instruction {
    private String myVar;
    private double myStart, myEnd, myIncrement;

    public InstructionLoop (String parameters, Instruction parent) {
        super(1, parent); // only child is an InstructionListNode
        setParameters(parameters);
    }

    protected abstract void setParameters (String parameters);

    @Override
    public Instruction eval () {
        Instruction ret = null;
        for (double i = myStart; i < myEnd + 1; i += myIncrement) {
            Model.getVariableCache().put(myVar, i);
            // TODO: should this variable be taken out of scope after the loop?
            // TODO: well we should check if it already existed first
            ret = getChildren().get(0).eval();
        }
        return ret;
    }

    protected void setVar (String var) {
        myVar = var;
    }

    protected void setStart (double start) {
        myStart = start;
    }

    protected void setEnd (double end) {
        myEnd = end;
    }

    protected void setIncrement (double increment) {
        myIncrement = increment;
    }
}
