package model.instruction.error;

/**
 * 
 * Invalid command error for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InvalidCommand extends Exception {
    public static final String MESSAGE = "Invalid command entered";

    public InvalidCommand () {
        super(MESSAGE);
    }

}
