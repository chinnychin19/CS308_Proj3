package model.instruction.turtle;

import model.Model;
import model.Turtle;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionHEADING extends Instruction {

    public InstructionHEADING (Instruction parent, Model m) {
        super(0, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        double ret = 0;
        for (Turtle t : getModel().getActiveTurtles()) {
            ret = t.getAngle();
        }
        return new InstructionConstant(ret, null, getModel());
    }

}
