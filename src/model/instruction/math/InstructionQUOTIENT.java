package model.instruction.math;

import model.Model;
import model.instruction.Instruction;
import model.instruction.error.DivideByZero;
import model.instruction.InstructionConstant;


/**
 * 
 * Quotient instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionQUOTIENT extends Instruction {

    public InstructionQUOTIENT (Instruction parent, Model m) {
        super(2, parent, m); // Quotient takes two parameters
    }

    @Override
    public Instruction eval () throws Exception {
        double a = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        double b = ((InstructionConstant) getChildren().get(1).eval()).getValue();
        if (b == 0) { throw new DivideByZero(); }
        double c = (int) (a / b);

        return new InstructionConstant(c, null, getModel());
    }

}
