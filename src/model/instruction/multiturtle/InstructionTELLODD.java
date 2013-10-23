package model.instruction.multiturtle;

import model.Model;
import model.Turtle;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


/**
 * 
 * Tellodd instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionTELLODD extends Instruction {

    public InstructionTELLODD (Instruction parent, Model m) {
        super(0, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        int ret = 0;
        for (Turtle t : getModel().getActiveTurtles()) {
            int id = t.getID();
            if (id % 2 == 1) { // odd
                ret = id; // this way it returns only the last odd id
                t.setActive(true);
            }
            else { // even
                t.setActive(false);
            }
        }
        return new InstructionConstant(ret, null, getModel());
    }

}
