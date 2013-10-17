package model.instruction.turtle;

import model.Model;
import model.Turtle;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionHIDETURTLE extends Instruction {

    public InstructionHIDETURTLE (Instruction parent, Model m) {
        super(0, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        for (Turtle t : getModel().getActiveTurtles()) {
            t.setVisible(false);
        }
        return new InstructionConstant(0, null, getModel());
    }

}
