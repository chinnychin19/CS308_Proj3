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
        double radians = degrees * Math.PI / PI_DEGREES;
        double ret = Math.cos(radians);

        return new InstructionConstant(ret, null);
    }

}
