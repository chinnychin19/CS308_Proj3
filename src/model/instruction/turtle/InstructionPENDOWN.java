package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionPENDOWN extends Instruction {

    public InstructionPENDOWN (Instruction parent) {
        super(0, parent);
    }

    @Override
    public Instruction eval () {
        Model.getTurtle().setDrawing(true);
        return new InstructionConstant(1, null);
    }

}
