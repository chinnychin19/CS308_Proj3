package model.instruction.math;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionRANDOM extends Instruction {

    public InstructionRANDOM (Instruction parent) {
        super(1, parent); // Random takes one parameter
    }

    @Override
    public Instruction eval () {
        double a = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        a = (int) (a * Math.random());

        return new InstructionConstant(a, null);
    }

}
