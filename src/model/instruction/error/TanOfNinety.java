package model.instruction.error;

/**
 * 
 * Tan of 90 degrees error for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class TanOfNinety extends Exception {
    public static final String MESSAGE = "TAN of 90 degrees undefined";

    public TanOfNinety () {
        super(MESSAGE);
    }

}
