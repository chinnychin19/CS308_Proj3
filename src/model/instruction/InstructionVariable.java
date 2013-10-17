package model.instruction;

import model.Model;


public class InstructionVariable extends InstructionConstant {
    private String myName;

    public InstructionVariable (String name, Instruction parent, Model m) {
        super(m.getVariableCache().get(name), parent, m);
        myName = name;
    }

    @Override
    public double getValue () {
        return getModel().getVariableCache().get(myName);
    }

    @Override
    public Instruction eval () throws Exception {
        return new InstructionConstant(getModel().getVariableCache().get(myName), null, getModel());
    }

    public String getName () {
        return myName;
    }

}
