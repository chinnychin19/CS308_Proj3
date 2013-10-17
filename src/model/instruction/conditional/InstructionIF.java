package model.instruction.conditional;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionIF extends InstructionConditional {
    public InstructionIF (Instruction parent, Model m) {
        super(2, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        double condition = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        if (Math.abs(condition) > InstructionConditional.EPSILON) {
            return (InstructionConstant) getChildren().get(1)
                    .eval();
        }
        else {
            return new InstructionConstant(0, null, getModel());
        }
    }
}
