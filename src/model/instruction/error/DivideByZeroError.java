package model.instruction.error;

import model.instruction.Instruction;

public class DivideByZeroError extends Error {
    public static final String MESSAGE = "/ by zero error";
    public DivideByZeroError () {
        super(MESSAGE, null);
    }
    
}
