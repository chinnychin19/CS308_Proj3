package model.instruction.math;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionMINUS extends Instruction {

    public InstructionMINUS (Instruction parent) {
        super(1, parent);
    }

    @Override
    public Instruction eval () {
        double ret = ((InstructionConstant) getChildren().get(0).eval()).getValue() * -1;

        return new InstructionConstant(ret, null);
    }

}
