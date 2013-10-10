package model.instruction;

public class InstructionIF extends InstructionConditional {
    public InstructionIF (Instruction parent) {
        super(2, parent);
    }

    @Override
    public Instruction eval () {
        double condition = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        if (Math.abs(condition) > InstructionConditional.EPSILON) {
            return (InstructionConstant) getChildren().get(1)
                    .eval();
        }
        else {
            return new InstructionConstant(0, null);
        }
    }
}
