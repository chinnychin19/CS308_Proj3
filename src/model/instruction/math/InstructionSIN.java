package model.instruction.math;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionSIN extends Instruction {

    private static final int PI_DEGREES = 180;

    public InstructionSIN (Instruction parent) {
        super(1, parent); // Sine takes one parameter
    }

    @Override
    public Instruction eval () throws Exception {
        double degrees = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        double radians = degrees * Math.PI / PI_DEGREES;
        double ret = Math.sin(radians);

        return new InstructionConstant(ret, null);
    }

}
