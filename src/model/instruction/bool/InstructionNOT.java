package model.instruction.bool;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


/**
 * 
 * Not instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionNOT extends Instruction {

    public InstructionNOT (Instruction parent, Model m) {
        super(1, parent, m); // Not takes one parameter
    }

    @Override
    public Instruction eval () throws Exception {
        double a = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        a = a == 0 ? 1 : 0;

        return new InstructionConstant(a, null, getModel());
    }

}
