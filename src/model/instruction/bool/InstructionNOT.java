package model.instruction.bool;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionNOT extends Instruction {

    public InstructionNOT (Instruction parent) {
        super(1, parent); // Not takes one parameter
    }

    @Override
    public Instruction eval () {
        double a = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        a = a == 0 ? 1 : 0;

        return new InstructionConstant(a, null);
    }

}
