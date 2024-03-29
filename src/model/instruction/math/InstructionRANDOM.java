package model.instruction.math;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;
import model.instruction.error.NonPositiveRandom;


/**
 * 
 * Random instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionRANDOM extends Instruction {

    public InstructionRANDOM (Instruction parent, Model m) {
        super(1, parent, m); // Random takes one parameter
    }

    @Override
    public Instruction eval () throws Exception {
        double a = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        if (a <= 0) { throw new NonPositiveRandom(); }
        a = (int) (a * Math.random());

        return new InstructionConstant(a, null, getModel());
    }

}
