package model.instruction.turtle;

import model.Model;
import model.Turtle;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


/**
 * 
 * Setheading instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionSETHEADING extends Instruction {

    public InstructionSETHEADING (Instruction parent, Model m) {
        super(1, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        Instruction param = getChildren().get(0).eval();
        double angle = ((InstructionConstant) param).getValue();
        double deltaAngle = 0;
        for (Turtle t : getModel().getActiveTurtles()) {
            deltaAngle = t.doAbsoluteRotate(angle);
        }
        return new InstructionConstant(deltaAngle, null, getModel());
    }

}
