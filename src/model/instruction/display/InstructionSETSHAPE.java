package model.instruction.display;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


/**
 * 
 * Setshape instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionSETSHAPE extends Instruction {

    public InstructionSETSHAPE (Instruction parent, Model m) {
        super(1, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        Instruction child = getChildren().get(0).eval();
        double shapeIndex = ((InstructionConstant) child).getValue();
        int shapeIndexAsInteger = (int) Math.round(shapeIndex);
        String status = getModel().setShape(shapeIndexAsInteger);
        if (!status.isEmpty()) { throw new Exception(status); }
        return new InstructionConstant(shapeIndex, null, getModel());
    }

}
