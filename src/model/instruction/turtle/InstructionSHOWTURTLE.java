package model.instruction.turtle;

import model.Model;
import model.Turtle;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionSHOWTURTLE extends Instruction {

    public InstructionSHOWTURTLE (Instruction parent, Model m) {
        super(0, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        for (Turtle t : getModel().getActiveTurtles()) {
            t.setVisible(true);
        }
        return new InstructionConstant(1, null, getModel());
    }

}
