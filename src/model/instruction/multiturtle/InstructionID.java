package model.instruction.multiturtle;

import model.Model;
import model.Turtle;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


/**
 * 
 * ID instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionID extends Instruction {

    public InstructionID (Instruction parent, Model m) {
        super(0, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        double id = 0;
        for (Turtle t : getModel().getActiveTurtles()) {
            id = t.getID();
        }
        return new InstructionConstant(id, null, getModel());
    }

}
