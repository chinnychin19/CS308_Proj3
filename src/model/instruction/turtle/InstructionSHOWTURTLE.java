package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;

public class InstructionSHOWTURTLE extends Instruction {

    public InstructionSHOWTURTLE (Instruction parent) {
        super(0, parent);
    }

    @Override
    public Instruction eval () {
        Model.getTurtle().setVisible(true);
        return new InstructionConstant(1, null);
    }

}
