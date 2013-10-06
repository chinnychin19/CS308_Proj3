package model.instruction.math;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionTAN extends Instruction {

    public InstructionTAN (Instruction parent) {
        super(1, parent);
    }

    @Override
    public Instruction eval () {
        double degrees = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        double radians = degrees / 180 * Math.PI;

        radians = Math.tan(radians); // Tan? Or other command to take other quadrants into account?
        degrees = radians / Math.PI * 180;

        return new InstructionConstant(degrees, null);
    }

}
