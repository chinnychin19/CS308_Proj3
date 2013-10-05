package model.instruction;

import model.Model;


public class InstructionFD extends Instruction {

    public InstructionFD (Instruction parent) {
        super(1, parent); // FD takes 1 parameter
    }

    @Override
    public Instruction eval () {
        Instruction ret = getChildren().get(0).eval();
        double pixels = ((InstructionConstant) ret).getValue();
        Model.getTurtle().forward(pixels);
        return ret;
    }
}
