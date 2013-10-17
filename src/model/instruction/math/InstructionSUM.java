package model.instruction.math;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionSUM extends Instruction {

    public InstructionSUM (Instruction parent, Model m) {
        super(2, parent, m); // Sum takes two parameters
    }

    @Override
    public Instruction eval () throws Exception {
        double a = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        double b = ((InstructionConstant) getChildren().get(1).eval()).getValue();
        double c = a + b;

        return new InstructionConstant(c, null, getModel());
    }
}
