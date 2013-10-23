package model.instruction.bool;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


/**
 * 
 * Notequal? instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionNOTEQUALP extends Instruction {

    public InstructionNOTEQUALP (Instruction parent, Model m) {
        super(2, parent, m); // Not equal takes two parameters
    }

    @Override
    public Instruction eval () throws Exception {
        double a = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        double b = ((InstructionConstant) getChildren().get(1).eval()).getValue();
        double c = a != b ? 1 : 0;

        return new InstructionConstant(c, null, getModel());
    }

}
