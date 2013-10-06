package model.instruction.math;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionATAN extends Instruction {

    public InstructionATAN (Instruction parent) {
        super(1, parent);
    }

    @Override
    public Instruction eval () {
        double degrees = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        double radians = degrees / 180 * Math.PI;

        radians = Math.atan(radians); // Atan at 0?
        degrees = radians / Math.PI * 180;

        return new InstructionConstant(degrees, null);
    }

}
