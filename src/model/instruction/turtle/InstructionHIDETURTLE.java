package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionHIDETURTLE extends Instruction {

    public InstructionHIDETURTLE (Instruction parent, Model m) {
        super(0, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        getModel().getTurtle().setVisible(false);
        return new InstructionConstant(0, null, getModel());
    }

}
