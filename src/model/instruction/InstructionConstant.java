package model.instruction;

public class InstructionConstant extends Instruction {
    private double myValue;

    public InstructionConstant (double value, Instruction parent) {
        super(0, parent);
        myValue = value;
    }

    @Override
    public Instruction eval () {
        return this;
    }

    public double getValue () {
        return myValue;
    }
}
