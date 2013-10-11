package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionSHOWINGP extends Instruction {

    public InstructionSHOWINGP (Instruction parent) {
        super(0, parent);
    }

    @Override
    public Instruction eval () {
        double ret = Model.getTurtle().isVisible() ? 1 : 0;
        return new InstructionConstant(ret, null);
    }

}
