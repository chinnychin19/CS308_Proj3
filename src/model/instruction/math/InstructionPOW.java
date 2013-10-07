package model.instruction.math;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionPOW extends Instruction {

    public InstructionPOW (Instruction parent) {
        super(1, parent); // Power takes one parameter
    }

    @Override
    public Instruction eval () {
        // TODO: What is it power to?
        double a = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        a *= a;

        return new InstructionConstant(a, null);
    }

}
