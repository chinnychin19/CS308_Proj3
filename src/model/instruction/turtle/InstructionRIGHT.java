package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionRIGHT extends Instruction {

    public InstructionRIGHT (Instruction parent, Model m) {
        super(1, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        Instruction ret = getChildren().get(0).eval();
        double angle = ((InstructionConstant) ret).getValue();
        getModel().getTurtle().doRelativeRotate(-angle); // turns CW
        return ret;
    }

}
