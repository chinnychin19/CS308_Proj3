package model.instruction.display;

import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


/**
 * 
 * Clearstamps instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionCLEARSTAMPS extends Instruction {

    public InstructionCLEARSTAMPS (Instruction parent, Model m) {
        super(0, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        getModel().clearStamps();
        return new InstructionConstant(0, null, getModel());
    }

}
