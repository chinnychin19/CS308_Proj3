package model.instruction.math;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


/**
 * 
 * Sin instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionSIN extends Instruction {

    private static final int PI_DEGREES = 180;

    public InstructionSIN (Instruction parent, Model m) {
        super(1, parent, m); // Sine takes one parameter
    }

    @Override
    public Instruction eval () throws Exception {
        double degrees = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        double radians = degrees * Math.PI / PI_DEGREES;
        double ret = Math.sin(radians);

        return new InstructionConstant(ret, null, getModel());
    }

}
