package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionHEADING extends Instruction {

    public InstructionHEADING (Instruction parent, Model m) {
        super(0, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        double ret = getModel().getTurtle().getAngle();
        return new InstructionConstant(ret, null, getModel());
    }

}
