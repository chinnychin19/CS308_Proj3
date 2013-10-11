package model.instruction.conditional;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionIFELSE extends InstructionConditional {

    public InstructionIFELSE (Instruction parent) {
        super(3, parent);
    }

    @Override
    public Instruction eval () {
        double condition = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        int index = Math.abs(condition) > InstructionConditional.EPSILON ? 1 : 2;
        return (InstructionConstant) getChildren().get(index).eval();
    }
}
