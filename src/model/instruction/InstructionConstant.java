package model.instruction;

public class InstructionConstant extends Instruction {
    private double myValue;

    public InstructionConstant (double value) {
        super(0);
        myValue = value;
    }

    @Override
    protected Instruction eval () {
        return this;
    }

    public double getValue () {
        return myValue;
    }
}
