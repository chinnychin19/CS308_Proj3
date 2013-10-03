package model;

public class Model {
    protected Interpreter myInterpreter;
    protected CommandCache myCommandCache;
    protected VariableCache myVariableCache;
    protected InstructionQueue myInstructionQueue;
    protected Turtle myTurtle;
    protected CommandHistory myCommandHistory;

    public Model() {
        //TODO: this class should be a singleton instance and instantiate all private fields
    }
    public Interpreter getInterpreter () {
        return myInterpreter;
    }

    public CommandCache getCommandCache () {
        return myCommandCache;
    }

    public VariableCache getVariableCache () {
        return myVariableCache;
    }

    public InstructionQueue getInstructionQueue () {
        return myInstructionQueue;
    }

    public Turtle getTurtle () {
        return myTurtle;
    }

    public CommandHistory getMyCommandHistory () {
        return myCommandHistory;
    }
}
