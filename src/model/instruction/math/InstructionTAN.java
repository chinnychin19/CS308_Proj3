package model.instruction.math;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;
import model.instruction.error.TanOfNinety;


public class InstructionTAN extends Instruction {

    private static final int PI_DEGREES = 180;

    public InstructionTAN (Instruction parent) {
        super(1, parent); // Tan takes one parameter
    }

    @Override
    public Instruction eval () throws Exception {
        double degrees = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        if (degrees == 90) { throw new TanOfNinety(); }
        double radians = degrees * Math.PI / PI_DEGREES;
        double ret = Math.tan(radians);

        return new InstructionConstant(ret, null);
    }

}
