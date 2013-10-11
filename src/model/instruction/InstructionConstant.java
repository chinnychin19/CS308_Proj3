package model.instruction;

public class InstructionConstant extends Instruction {
    private double myValue;

    public InstructionConstant (double value, Instruction parent) {
        super(0, parent);
        myValue = value;
    }

    @Override
    public Instruction eval () throws Exception {
        return new InstructionConstant(myValue, null);
    }

    public double getValue () {
        return myValue;
    }
}
