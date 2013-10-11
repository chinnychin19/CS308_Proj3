package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionHEADING extends Instruction {

    public InstructionHEADING (Instruction parent) {
        super(0, parent);
    }

    @Override
    public Instruction eval () {
        double ret = Model.getTurtle().getAngle();
        return new InstructionConstant(ret, null);
    }

}
