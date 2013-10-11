package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionBACK extends Instruction {

    public InstructionBACK (Instruction parent) {
        super(1, parent);
    }

    @Override
    public Instruction eval () throws Exception {
        Instruction ret = getChildren().get(0).eval();
        double pixels = ((InstructionConstant) ret).getValue();
        Model.getTurtle().doRelativeMove(-pixels); // - makes it go backwards
        return ret;
    }
}
