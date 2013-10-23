package model.instruction.turtle;

import model.Model;
import model.Turtle;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


/**
 * 
 * Xcor instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionXCOR extends Instruction {

    public InstructionXCOR (Instruction parent, Model m) {
        super(0, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        double ret = 0;
        for (Turtle t : getModel().getActiveTurtles()) {
            ret = t.getX();
        }
        return new InstructionConstant(ret, null, getModel());
    }

}
