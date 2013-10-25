package model.instruction.math;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


/**
 * 
 * Atan instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionATAN extends Instruction {

    private static final double PI_DEGREES = 180;

    public InstructionATAN (Instruction parent, Model m) {
        super(1, parent, m); // Arctan takes one parameter
    }

    @Override
    public Instruction eval () throws Exception {
        // TODO: Atan at 0? atan2?
        double degrees = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        double radians = degrees * Math.PI / PI_DEGREES;
        double ret = Math.atan(radians);

        return new InstructionConstant(ret, null, getModel());
    }

}
