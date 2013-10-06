package model.instruction.math;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionSINE extends Instruction {

    public InstructionSINE (Instruction parent) {
        super(1, parent);
    }

    @Override
    public Instruction eval () {
        double degrees = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        double radians = degrees / 180 * Math.PI;

        radians = Math.sin(radians);
        degrees = radians / Math.PI * 180;

        return new InstructionConstant(degrees, null);
    }

}
