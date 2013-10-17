package model.instruction.math;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionMINUS extends Instruction {

    public InstructionMINUS (Instruction parent, Model m) {
        super(1, parent, m); // Minus takes one parameter
    }

    @Override
    public Instruction eval () throws Exception {
        double a = ((InstructionConstant) getChildren().get(0).eval()).getValue() * -1;

        return new InstructionConstant(a, null, getModel());
    }

}
