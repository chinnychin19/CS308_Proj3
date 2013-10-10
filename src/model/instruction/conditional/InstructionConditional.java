package model.instruction.conditional;

import model.instruction.Instruction;


public abstract class InstructionConditional extends Instruction {
    public static final double EPSILON = 0.0000001;

    public InstructionConditional (int paramCount, Instruction parent) {
        super(paramCount, parent);
    }
}
