package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionHOME extends Instruction {

    public InstructionHOME (Instruction parent) {
        super(0, parent);
    }

    @Override
    public Instruction eval () {
        double dist = Model.getTurtle().doAbsoluteMove(0, 0);
        return new InstructionConstant(dist, null);
    }

}
