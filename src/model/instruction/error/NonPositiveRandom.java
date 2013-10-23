package model.instruction.error;

/**
 * 
 * Non positive random error for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class NonPositiveRandom extends Exception {
    public static final String MESSAGE = "max of RANDOM must be strictly positive";

    public NonPositiveRandom () {
        super(MESSAGE);
    }
}
