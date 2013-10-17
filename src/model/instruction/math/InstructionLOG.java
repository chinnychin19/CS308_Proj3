package model.instruction.math;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;
import model.instruction.error.LogOfZero;


public class InstructionLOG extends Instruction {

    public InstructionLOG (Instruction parent, Model m) {
        super(1, parent, m); // Log takes one parameter
    }

    @Override
    public Instruction eval () throws Exception {
        double a = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        if (a == 0) { throw new LogOfZero(); }
        a = Math.log(a);

        return new InstructionConstant(a, null, getModel());
    }
}
