package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionTOWARDS extends Instruction {

    public InstructionTOWARDS (Instruction parent) {
        super(2, parent);
    }

    @Override
    public Instruction eval () {
        Instruction param1 = getChildren().get(0).eval();
        double x = ((InstructionConstant) param1).getValue();
        Instruction param2 = getChildren().get(0).eval();
        double y = ((InstructionConstant) param2).getValue();
        double deltaAngle = Model.getTurtle().doRotateTowards(x, y);
        return new InstructionConstant(deltaAngle, null);
    }

}
