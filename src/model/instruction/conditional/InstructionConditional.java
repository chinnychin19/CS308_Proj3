package model.instruction.conditional;

import model.Model;
import model.instruction.ComplexParameterInstruction;
import model.instruction.Instruction;


/**
 * 
 * Abstract class for conditional instructions
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public abstract class InstructionConditional extends Instruction implements
        ComplexParameterInstruction {
    public static final double EPSILON = 0.0000001;

    public InstructionConditional (int paramCount, Instruction parent, Model m) {
        super(paramCount, parent, m);
    }
}
