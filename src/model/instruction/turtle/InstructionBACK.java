package model.instruction.turtle;

import model.Model;
import model.Turtle;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


/**
 * 
 * Back instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionBACK extends Instruction {

    public InstructionBACK (Instruction parent, Model m) {
        super(1, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        Instruction ret = getChildren().get(0).eval();
        double pixels = ((InstructionConstant) ret).getValue();
        for (Turtle t : getModel().getActiveTurtles()) {
            t.doRelativeMove(-pixels); // - makes it go backwards
        }
        return ret;
    }
}
