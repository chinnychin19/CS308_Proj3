package model.instruction.math;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionREMAINDER extends Instruction {

    public InstructionREMAINDER (Instruction parent) {
        super(2, parent); // Remainder takes two parameters
    }

    @Override
    public Instruction eval () {
        double a = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        double b = ((InstructionConstant) getChildren().get(1).eval()).getValue();
        double c = (int) (a % b);

        return new InstructionConstant(c, null);
    }

}
