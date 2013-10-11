package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionYCOR extends Instruction {

    public InstructionYCOR (Instruction parent) {
        super(0, parent);
    }

    @Override
    public Instruction eval () throws Exception {
        double ret = Model.getTurtle().getY();
        return new InstructionConstant(ret, null);
    }

}
