package model.instruction.math;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionMINUS extends Instruction {

    public InstructionMINUS (Instruction parent) {
        super(1, parent); // Minus takes one parameter
    }

    @Override
    public Instruction eval () {
        double a = ((InstructionConstant) getChildren().get(0).eval()).getValue() * -1;

        return new InstructionConstant(a, null);
    }

}
