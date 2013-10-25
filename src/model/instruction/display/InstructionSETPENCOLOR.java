package model.instruction.display;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


/**
 * 
 * Setpencolor instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionSETPENCOLOR extends Instruction {

    public InstructionSETPENCOLOR (Instruction parent, Model m) {
        super(1, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        Instruction child = getChildren().get(0).eval();
        double colorIndex = ((InstructionConstant) child).getValue();
        int colorIndexAsInteger = (int) Math.round(colorIndex);
        String status = getModel().setPenColor(colorIndexAsInteger);
        if (!status.isEmpty()) { throw new Exception(status); }
        return new InstructionConstant(colorIndex, null, getModel());
    }

}
