package model.instruction.error;

import model.Model;


/**
 * 
 * Too few parameters error for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class TooFewParametersInstruction extends ErrorInstruction {
    public static final String MESSAGE = "Too few parameters were passed into the function";

    public TooFewParametersInstruction (Model m) {
        super(MESSAGE, m);
    }
}
