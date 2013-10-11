package model.instruction.error;

public class TooFewParametersInstruction extends ErrorInstruction {
    public static final String MESSAGE = "Too few parameters were passed into the function";
    
    public TooFewParametersInstruction() {
        super(MESSAGE);
    }
}
