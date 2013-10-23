package model.instruction.display;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


/**
 * 
 * Setpalette instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionSETPALETTE extends Instruction {

    public InstructionSETPALETTE (Instruction parent, Model m) {
        super(4, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        Instruction indexChild = getChildren().get(0).eval();
        Instruction rChild = getChildren().get(1).eval();
        Instruction gChild = getChildren().get(2).eval();
        Instruction bChild = getChildren().get(3).eval();
        int colorIndex = (int) Math.round(((InstructionConstant) indexChild).getValue());
        int r = (int) Math.round(((InstructionConstant) rChild).getValue());
        int g = (int) Math.round(((InstructionConstant) gChild).getValue());
        int b = (int) Math.round(((InstructionConstant) bChild).getValue());
        String status = getModel().setPalette(colorIndex, r, g, b);
        if (!status.isEmpty()) { throw new Exception(status); }
        return new InstructionConstant(colorIndex, null, getModel());
    }

}
