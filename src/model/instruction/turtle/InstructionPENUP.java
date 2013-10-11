package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionPENUP extends Instruction {

    public InstructionPENUP (Instruction parent) {
        super(0, parent);
    }

    @Override
    public Instruction eval () throws Exception {
        Model.getTurtle().setDrawing(false);
        return new InstructionConstant(0, null);
    }

}
