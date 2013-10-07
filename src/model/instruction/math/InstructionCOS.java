package model.instruction.math;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionCOS extends Instruction {

    private static final int PI_DEGREES = 180;

    public InstructionCOS (Instruction parent) {
        super(1, parent); // Cos takes one parameter
    }

    @Override
    public Instruction eval () {
        double degrees = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        double radians = degrees / PI_DEGREES * Math.PI;

        radians = Math.cos(radians);
        degrees = radians / Math.PI * PI_DEGREES;

        return new InstructionConstant(degrees, null);
    }

}
