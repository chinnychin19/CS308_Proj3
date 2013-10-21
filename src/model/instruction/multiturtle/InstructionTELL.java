package model.instruction.multiturtle;

import model.Model;
import model.Turtle;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionTELL extends Instruction {

    public InstructionTELL (Instruction parent, Model m) {
        super(Integer.MAX_VALUE, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        int id = 0;
        for (Turtle t : getModel().getActiveTurtles()) {
            t.setActive(false);
        }
        for (Instruction child : getChildren()) {
            id = (int) Math.round(((InstructionConstant) child.eval()).getValue());
            getModel().getTurtle(id).setActive(true);
        }
        return new InstructionConstant(id, null, getModel());
    }

}
