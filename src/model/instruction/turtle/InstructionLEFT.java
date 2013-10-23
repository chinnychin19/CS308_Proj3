package model.instruction.turtle;

import model.Model;
import model.Turtle;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


/**
 * 
 * Left instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionLEFT extends Instruction {

    public InstructionLEFT (Instruction parent, Model m) {
        super(1, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        Instruction ret = getChildren().get(0).eval();
        double angle = ((InstructionConstant) ret).getValue();
        for (Turtle t : getModel().getActiveTurtles()) {
            t.doRelativeRotate(angle); // turns CCW
        }
        return ret;
    }

}
