package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionCLEARSCREEN extends Instruction {

    public InstructionCLEARSCREEN (Instruction parent) {
        super(0, parent);
    }

    @Override
    public Instruction eval () {
        double dist = Model.getTurtle().doAbsoluteMove(0, 0);
        Model.getTurtle().clearPaths();
        return new InstructionConstant(dist, null);
    }

}
