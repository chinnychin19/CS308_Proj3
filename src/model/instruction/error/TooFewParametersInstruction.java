package model.instruction.error;

import model.Model;


public class TooFewParametersInstruction extends ErrorInstruction {
    public static final String MESSAGE = "Too few parameters were passed into the function";

    public TooFewParametersInstruction (Model m) {
        super(MESSAGE, m);
    }
}
