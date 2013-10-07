package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionPENDOWNP extends Instruction {

    public InstructionPENDOWNP (Instruction parent) {
        super(0, parent);
    }

    @Override
    public Instruction eval () {
        double ret = Model.getTurtle().isDrawing() ? 1 : 0;
        return new InstructionConstant(ret, null);
    }

}