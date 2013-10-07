package model.instruction.math;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionATAN extends Instruction {

    private static final double PI_DEGREES = 180;

    public InstructionATAN (Instruction parent) {
        super(1, parent); // Arctan takes one parameter
    }

    @Override
    public Instruction eval () {
        // TODO: Atan at 0? atan2?
        double degrees = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        double radians = degrees / PI_DEGREES * Math.PI;

        radians = Math.atan(radians);
        degrees = radians / Math.PI * PI_DEGREES;

        return new InstructionConstant(degrees, null);
    }

}
