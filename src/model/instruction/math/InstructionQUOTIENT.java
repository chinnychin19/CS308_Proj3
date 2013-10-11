package model.instruction.math;

import model.instruction.Instruction;
import model.instruction.error.DivideByZero;
import model.instruction.error.ErrorInstruction;
import model.instruction.InstructionConstant;


public class InstructionQUOTIENT extends Instruction {

    public InstructionQUOTIENT (Instruction parent) {
        super(2, parent); // Quotient takes two parameters
    }

    @Override
    public Instruction eval () throws Exception {
        double a = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        double b = ((InstructionConstant) getChildren().get(1).eval()).getValue();
        if (b == 0) {
            throw new DivideByZero();
        }
        double c = (int) (a / b);

        return new InstructionConstant(c, null);
    }

}
