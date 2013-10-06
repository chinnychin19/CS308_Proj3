package model.instruction.math;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionPOW extends Instruction {

    public InstructionPOW (Instruction parent) {
        super(1, parent);
    }

    @Override
    public Instruction eval () {
        // TODO: What is it power to?
        double ret = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        ret *= ret;

        return new InstructionConstant(ret, null);
    }

}
