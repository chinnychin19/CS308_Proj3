package model.instruction.math;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionLOG extends Instruction {

    public InstructionLOG (Instruction parent) {
        super(1, parent);
    }

    @Override
    public Instruction eval () {
        double ret = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        ret = Math.log(ret);

        return new InstructionConstant(ret, null);
    }

}
