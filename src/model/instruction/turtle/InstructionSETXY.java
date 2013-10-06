package model.instruction.turtle;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionSETXY extends Instruction {

    public InstructionSETXY (Instruction parent) {
        super(2, parent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Instruction eval () {
        Instruction param1 = getChildren().get(0).eval();
        double x = ((InstructionConstant) param1).getValue();
        Instruction param2 = getChildren().get(0).eval();
        double y = ((InstructionConstant) param2).getValue();
        double dist = Model.getTurtle().doAbsoluteMove(x, y);
        return new InstructionConstant(dist, null);
    }

}
