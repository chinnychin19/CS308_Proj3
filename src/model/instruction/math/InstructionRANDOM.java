package model.instruction.math;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionRANDOM extends Instruction {

    public InstructionRANDOM (Instruction parent) {
        super(1, parent);
    }

    @Override
    public Instruction eval () {
        double ret = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        ret = (int) (ret * Math.random());

        return new InstructionConstant(ret, null);
    }

}
