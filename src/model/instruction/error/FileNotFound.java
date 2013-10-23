package model.instruction.error;

/**
 * 
 * File not found error for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class FileNotFound extends Exception {
    public static final String MESSAGE = "File was not found";

    public FileNotFound () {
        super(MESSAGE);
    }
}
