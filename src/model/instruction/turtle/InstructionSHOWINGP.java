package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionSHOWINGP extends Instruction {

    public InstructionSHOWINGP (Instruction parent, Model m) {
        super(0, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        double ret = getModel().getTurtle().isVisible() ? 1 : 0;
        return new InstructionConstant(ret, null, getModel());
    }

}
