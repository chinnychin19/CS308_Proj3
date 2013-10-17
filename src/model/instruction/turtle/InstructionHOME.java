package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionHOME extends Instruction {

    public InstructionHOME (Instruction parent, Model m) {
        super(0, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        double dist = getModel().getTurtle().doAbsoluteMove(0, 0);
        return new InstructionConstant(dist, null, getModel());
    }

}
