package model;

public class Model {
    protected Interpreter myInterpreter;
    protected CommandCache myCommandCache;
    protected VariableCache myVariableCache;
    protected InstructionQueue myInstructionQueue;
    protected Turtle myTurtle;

    public Model() {
        //TODO: this class should be a singleton instance
    }
}
