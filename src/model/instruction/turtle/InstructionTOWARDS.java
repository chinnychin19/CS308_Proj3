package model.instruction.turtle;

import model.Model;
import model.Turtle;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


/**
 * 
 * Towards instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionTOWARDS extends Instruction {

    public InstructionTOWARDS (Instruction parent, Model m) {
        super(2, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        Instruction param1 = getChildren().get(0).eval();
        double x = ((InstructionConstant) param1).getValue();
        Instruction param2 = getChildren().get(1).eval();
        double y = ((InstructionConstant) param2).getValue();
        double deltaAngle = 0;
        for (Turtle t : getModel().getActiveTurtles()) {
            deltaAngle = t.doRotateTowards(x, y);
        }
        return new InstructionConstant(deltaAngle, null, getModel());
    }

}
