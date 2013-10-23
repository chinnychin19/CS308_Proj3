package model.instruction.error;

/**
 * 
 * Infinite loop error for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InfiniteLoop extends Exception {
    public final static String MESSAGE = "Entered an infinite loop";

    public InfiniteLoop () {
        super(MESSAGE);
    }
}
