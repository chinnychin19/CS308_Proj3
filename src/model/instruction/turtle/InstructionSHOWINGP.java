package model.instruction.turtle;

import model.Model;
import model.Turtle;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionSHOWINGP extends Instruction {

    public InstructionSHOWINGP (Instruction parent, Model m) {
        super(0, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        double ret = 0;
        for (Turtle t : getModel().getActiveTurtles()) {
            ret = t.isVisible() ? 1 : 0;
        }
        return new InstructionConstant(ret, null, getModel());
    }

}
