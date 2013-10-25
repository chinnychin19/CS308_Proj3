package model.instruction.math;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


/**
 * 
 * Pow instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionPOW extends Instruction {

    public InstructionPOW (Instruction parent, Model m) {
        super(2, parent, m); // Power takes one parameter
    }

    @Override
    public Instruction eval () throws Exception {
        double base = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        double exp = ((InstructionConstant) getChildren().get(1).eval()).getValue();

        return new InstructionConstant(Math.pow(base, exp), null, getModel());
    }

}
