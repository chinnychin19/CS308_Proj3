package model.instruction.display;

import model.Model;
import model.Turtle;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


/**
 * 
 * Shape instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionSHAPE extends Instruction {

    public InstructionSHAPE (Instruction parent, Model m) {
        super(0, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        double ret = 0;
        for (Turtle t : getModel().getActiveTurtles()) {
            ret = t.getShapeIndex();
        }
        return new InstructionConstant(ret, null, getModel());
    }

}
