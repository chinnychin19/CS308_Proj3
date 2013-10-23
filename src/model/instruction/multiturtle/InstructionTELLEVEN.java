package model.instruction.multiturtle;

import model.Model;
import model.Turtle;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


/**
 * 
 * Telleven instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionTELLEVEN extends Instruction {

    public InstructionTELLEVEN (Instruction parent, Model m) {
        super(0, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        int ret = 0;
        for (Turtle t : getModel().getActiveTurtles()) {
            int id = t.getID();
            if (id % 2 == 0) { // even
                ret = id; // this way it returns only the last even id
                t.setActive(true);
            }
            else { // odd
                t.setActive(false);
            }
        }
        return new InstructionConstant(ret, null, getModel());
    }

}
