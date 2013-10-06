package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionRIGHT extends Instruction {

    public InstructionRIGHT (Instruction parent) {
        super(1, parent);
    }

    @Override
    public Instruction eval () {
        Instruction ret = getChildren().get(0).eval();
        double angle = ((InstructionConstant) ret).getValue();
        Model.getTurtle().doRelativeRotate(-angle); // turns CW
        return ret;
    }

}
