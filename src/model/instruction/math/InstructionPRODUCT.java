package model.instruction.math;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionPRODUCT extends Instruction {

    public InstructionPRODUCT (Instruction parent) {
        super(2, parent); // Product takes two parameters
    }

    @Override
    public Instruction eval () throws Exception {
        double a = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        double b = ((InstructionConstant) getChildren().get(1).eval()).getValue();
        double c = a * b;

        return new InstructionConstant(c, null);
    }

}
