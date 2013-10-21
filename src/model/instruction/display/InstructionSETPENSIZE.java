package model.instruction.display;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionSETPENSIZE extends Instruction {

    public InstructionSETPENSIZE (Instruction parent, Model m) {
        super(1, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        Instruction child = getChildren().get(0).eval();
        double size = ((InstructionConstant) child).getValue();
        int sizeAsInteger = (int) Math.round(size);
        String status = getModel().setPenSize(sizeAsInteger);
        if (!status.isEmpty()) { throw new Exception(status); }
        return new InstructionConstant(size, null, getModel());
    }

}
