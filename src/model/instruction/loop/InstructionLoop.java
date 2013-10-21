package model.instruction.loop;

import model.Model;
import model.instruction.Instruction;
import model.instruction.error.InfiniteLoop;


public abstract class InstructionLoop extends Instruction {
    private String myVariable;
    private double myStart, myEnd, myIncrement;

    public InstructionLoop (Instruction parent, Model m) {
        super(1, parent, m); // only child is an InstructionListNode
        myVariable = "bogus";
        myStart = 0;
        myEnd = 0;
        myIncrement = 1;
    }

    public abstract void setParameters (String parameters) throws Exception;

    @Override
    public Instruction eval () throws Exception {
        Instruction ret = null;

        if (myIncrement < 0) {
            if (myEnd > myStart) { throw new InfiniteLoop(); }
            for (double i = myStart; i > myEnd + myIncrement / 2; i += myIncrement) {
                // myIncrement is negative so adding is "subtracting"
                getModel().getVariableCache().put(myVariable, i);
                ret = getChildren().get(0).eval();
            }
        }
        else if (myIncrement > 0) {
            if (myStart > myEnd) { throw new InfiniteLoop(); }
            for (double i = myStart; i < myEnd + myIncrement / 2; i += myIncrement) {
                // adding myIncrement/2 guarantees there won't be a double precision error to myEnd
                getModel().getVariableCache().put(myVariable, i);
                // TODO: should this variable be taken out of scope after the loop?
                // TODO: well we should check if it already existed first
                ret = getChildren().get(0).eval();
            }
        }
        else {
            throw new InfiniteLoop();
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
