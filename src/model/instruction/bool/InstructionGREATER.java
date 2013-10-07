package model.instruction.bool;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionGREATER extends Instruction {

    public InstructionGREATER (Instruction parent) {
        super(2, parent); // Greater takes two parameters
    }

    @Override
    public Instruction eval () {
        double a = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        double b = ((InstructionConstant) getChildren().get(1).eval()).getValue();
        double c = a > b ? 1 : 0;

        return new InstructionConstant(c, null);
    }

}