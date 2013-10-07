package model.instruction.math;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionLOG extends Instruction {

    public InstructionLOG (Instruction parent) {
        super(1, parent); // Log takes one parameter
    }

    @Override
    public Instruction eval () {
        double a = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        a = Math.log(a);

        return new InstructionConstant(a, null);
    }

}
