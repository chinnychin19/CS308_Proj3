package model.instruction.turtle;

import model.Model;
import model.Turtle;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


/**
 * 
 * Setxy instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionSETXY extends Instruction {

    public InstructionSETXY (Instruction parent, Model m) {
        super(2, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        Instruction param1 = getChildren().get(0).eval();
        double x = ((InstructionConstant) param1).getValue();
        Instruction param2 = getChildren().get(1).eval();
        double y = ((InstructionConstant) param2).getValue();
        double dist = 0;
        for (Turtle t : getModel().getActiveTurtles()) {
            t.doAbsoluteMove(x, y);
        }
        return new InstructionConstant(dist, null, getModel());
    }

}
