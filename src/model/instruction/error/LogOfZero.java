package model.instruction.error;

/**
 * 
 * Log of zero error for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class LogOfZero extends Exception {
    public static final String MESSAGE = "LOG of zero undefined";

    public LogOfZero () {
        super(MESSAGE);
    }
}
