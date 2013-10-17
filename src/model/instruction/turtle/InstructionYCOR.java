package model.instruction.turtle;

import model.Model;
import model.Turtle;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionYCOR extends Instruction {

    public InstructionYCOR (Instruction parent, Model m) {
        super(0, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        double ret = 0;
        for (Turtle t : getModel().getActiveTurtles()) {
            ret = t.getY();
        }
        return new InstructionConstant(ret, null, getModel());
    }

}
