package model.instruction.display;

import model.Model;
import model.Turtle;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


/**
 * 
 * Pencolor instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionPENCOLOR extends Instruction {

    public InstructionPENCOLOR (Instruction parent, Model m) {
        super(0, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        double ret = 0;
        for (Turtle t : getModel().getActiveTurtles()) {
            ret = t.getColorIndex(); // may throw ColorNotFound exception
        }
        return new InstructionConstant(ret, null, getModel());
    }

}
