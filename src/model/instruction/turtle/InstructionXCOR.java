package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionXCOR extends Instruction {

    public InstructionXCOR (Instruction parent) {
        super(0, parent);
    }

    @Override
    public Instruction eval () {
        double ret = Model.getTurtle().getX();
        return new InstructionConstant(ret, null);
    }

}
