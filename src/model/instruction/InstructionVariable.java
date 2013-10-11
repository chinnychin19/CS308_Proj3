package model.instruction;

import model.Model;


public class InstructionVariable extends InstructionConstant {
    private String myName;

    public InstructionVariable (String name, Instruction parent) {
        super(Model.getVariableCache().get(name), parent);
        myName = name;
    }

    @Override
    public double getValue () {
        return Model.getVariableCache().get(myName);
    }

    @Override
    public Instruction eval () {
        return new InstructionVariable(myName, null);
    }

    public String getName () {
        return myName;
    }

}
