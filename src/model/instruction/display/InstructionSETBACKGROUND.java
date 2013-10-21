package model.instruction.display;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionSETBACKGROUND extends Instruction {

    public InstructionSETBACKGROUND (Instruction parent, Model m) {
        super(1, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        Instruction child = getChildren().get(0).eval();
        double colorIndex = ((InstructionConstant) child).getValue();
        int colorIndexAsInteger = (int) Math.round(colorIndex);
        String status = getModel().setBGColor(colorIndexAsInteger);
        if (!status.isEmpty()) { throw new Exception(status); }
        return new InstructionConstant(colorIndex, null, getModel());
    }

}
