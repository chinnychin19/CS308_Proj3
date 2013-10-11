package model.instruction.math;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionATAN extends Instruction {

    private static final double PI_DEGREES = 180;

    public InstructionATAN (Instruction parent) {
        super(1, parent); // Arctan takes one parameter
    }

    @Override
    public Instruction eval () throws Exception {
        // TODO: Atan at 0? atan2?
        double degrees = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        double radians = degrees * Math.PI / PI_DEGREES;
        double ret = Math.atan(radians);

        return new InstructionConstant(ret, null);
    }

}
