package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;

public class InstructionSETHEADING extends Instruction {

    public InstructionSETHEADING (Instruction parent) {
        super(1, parent);
    }

    @Override
    public Instruction eval () {
        Instruction param = getChildren().get(0).eval();
        double angle = ((InstructionConstant) param).getValue();
        double deltaAngle = Model.getTurtle().doAbsoluteRotate(angle);
        return new InstructionConstant(deltaAngle, null);
    }

}
