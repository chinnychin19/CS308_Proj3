package model.instruction.error;

/**
 * 
 * Divide by zero error for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class DivideByZero extends Exception {
    public static final String MESSAGE = "/ by zero undefined";

    public DivideByZero () {
        super(MESSAGE);
    }
}
