package model.instruction.conditional;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionIFELSE extends InstructionConditional {

    public InstructionIFELSE (Instruction parent, Model m) {
        super(3, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        double condition = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        int index = Math.abs(condition) > InstructionConditional.EPSILON ? 1 : 2;
        return (InstructionConstant) getChildren().get(index).eval();
    }

    @Override
    public int getNumWords () {
        return 0;
    }

    @Override
    public int getNumExpressions () {
        return 1;
    }

    @Override
    public int getNumLists () {
        return 2;
    }
}
