package model.instruction.math;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;
import model.instruction.error.NonPositiveRandom;


public class InstructionRANDOM extends Instruction {

    public InstructionRANDOM (Instruction parent) {
        super(1, parent); // Random takes one parameter
    }

    @Override
    public Instruction eval () throws Exception {
        double a = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        if (a <= 0) { throw new NonPositiveRandom(); }
        a = (int) (a * Math.random());

        return new InstructionConstant(a, null);
    }

}
