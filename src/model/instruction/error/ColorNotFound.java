package model.instruction.error;

/**
 * 
 * Color not found error for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class ColorNotFound extends Exception {
    public static final String MESSAGE = "Color is no longer in palette";

    public ColorNotFound () {
        super(MESSAGE);
    }
}
