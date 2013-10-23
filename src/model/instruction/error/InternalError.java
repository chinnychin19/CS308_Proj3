package model.instruction.error;

/**
 * 
 * Internal error for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InternalError extends Exception {
    public static final String MESSAGE = "Oops! Something went wrong. We messed up. Our bad.";

    public InternalError () {
        super(MESSAGE);
    }
}
