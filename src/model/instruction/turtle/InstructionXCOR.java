package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionXCOR extends Instruction {

    public InstructionXCOR (Instruction parent, Model m) {
        super(0, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        double ret = getModel().getTurtle().getX();
        return new InstructionConstant(ret, null, getModel());
    }

}
