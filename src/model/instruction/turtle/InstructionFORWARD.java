package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionFORWARD extends Instruction {

    public InstructionFORWARD (Instruction parent, Model m) {
        super(1, parent, m); // FD takes 1 parameter
    }

    @Override
    public Instruction eval () throws Exception {
        Instruction ret = getChildren().get(0).eval();
        double pixels = ((InstructionConstant) ret).getValue();
        getModel().getTurtle().doRelativeMove(pixels);
        return ret;
    }
}
