package model.instruction.loop;

import model.Model;
import model.instruction.Instruction;


public abstract class InstructionLoop extends Instruction {
    private String myVariable;
    private double myStart, myEnd, myIncrement;

    public InstructionLoop (Instruction parent) {
        super(1, parent); // only child is an InstructionListNode
        myVariable = "bogus";
        myStart = 0;
        myEnd = 0;
        myIncrement = 1;
    }

    public abstract void setParameters (String parameters);

    @Override
    public Instruction eval () {
        Instruction ret = null;

        // TODO: Change this?
        if (myStart > myEnd && myIncrement < 0) {
            for (double i = myStart; i > myEnd - 1; i += myIncrement) {
                Model.getVariableCache().put(myVariable, i);
                ret = getChildren().get(0).eval();
            }
        }
        else {
            for (double i = myStart; i < myEnd + 1; i += myIncrement) {
                Model.getVariableCache().put(myVariable, i);
                // TODO: should this variable be taken out of scope after the loop?
                // TODO: well we should check if it already existed first
                ret = getChildren().get(0).eval();
            }
        }
        return ret;
    }

    protected void setVariable (String var) {
        myVariable = var;
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
