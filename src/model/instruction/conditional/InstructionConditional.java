package model.instruction.conditional;

import model.Model;
import model.instruction.Instruction;


public abstract class InstructionConditional extends Instruction {
    public static final double EPSILON = 0.0000001;

    public InstructionConditional (int paramCount, Instruction parent, Model m) {
        super(paramCount, parent, m);
    }
}
