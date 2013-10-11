package model.instruction.math;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;
import model.instruction.error.DivideByZero;


public class InstructionREMAINDER extends Instruction {

    public InstructionREMAINDER (Instruction parent) {
        super(2, parent); // Remainder takes two parameters
    }

    @Override
    public Instruction eval () throws Exception {
        double a = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        double b = ((InstructionConstant) getChildren().get(1).eval()).getValue();
        if (b == 0) { throw new DivideByZero(); }
        double c = (int) (a % b);

        return new InstructionConstant(c, null);
    }

}
