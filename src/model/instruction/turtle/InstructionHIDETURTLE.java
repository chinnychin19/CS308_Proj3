package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionHIDETURTLE extends Instruction {

    public InstructionHIDETURTLE (Instruction parent) {
        super(0, parent);
    }

    @Override
    public Instruction eval () {
        Model.getTurtle().setVisible(false);
        return new InstructionConstant(0, null);
    }

}
