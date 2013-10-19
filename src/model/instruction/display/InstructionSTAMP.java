package model.instruction.display;

import model.Model;
import model.Turtle;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionSTAMP extends Instruction {

    public InstructionSTAMP (Instruction parent, Model m) {
        super(0, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        for (Turtle t : getModel().getActiveTurtles()) {
            t.addStamp();
        }
        return new InstructionConstant(0, null, getModel());
    }

}
