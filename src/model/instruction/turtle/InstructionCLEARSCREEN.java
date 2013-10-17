package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionCLEARSCREEN extends Instruction {

    public InstructionCLEARSCREEN (Instruction parent, Model m) {
        super(0, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        double dist = getModel().getTurtle().doAbsoluteMove(0, 0);
        getModel().getTurtle().clearPaths();
        return new InstructionConstant(dist, null, getModel());
    }

}
