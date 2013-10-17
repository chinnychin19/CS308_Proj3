package model.instruction;

import model.Model;


public class InstructionConstant extends Instruction {
    private double myValue;

    public InstructionConstant (double value, Instruction parent, Model m) {
        super(0, parent, m);
        myValue = value;
    }

    @Override
    public Instruction eval () throws Exception {
        return new InstructionConstant(myValue, null, getModel());
    }

    public double getValue () {
        return myValue;
    }
}
