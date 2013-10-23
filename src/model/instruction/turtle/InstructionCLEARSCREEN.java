package model.instruction.turtle;

import model.Model;
import model.Turtle;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


/**
 * 
 * Clearscreen instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionCLEARSCREEN extends Instruction {

    public InstructionCLEARSCREEN (Instruction parent, Model m) {
        super(0, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        double dist = 0;
        for (Turtle t : getModel().getActiveTurtles()) {
            dist = t.doAbsoluteMove(0, 0);
            t.doAbsoluteRotate(90);
        }
        getModel().clearPaths();
        return new InstructionConstant(dist, null, getModel());
    }

}
