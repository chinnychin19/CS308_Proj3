package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionPENUP extends Instruction {

    public InstructionPENUP (Instruction parent, Model m) {
        super(0, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        getModel().getTurtle().setDrawing(false);
        return new InstructionConstant(0, null, getModel());
    }

}
