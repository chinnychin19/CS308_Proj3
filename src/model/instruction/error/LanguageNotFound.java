package model.instruction.error;

/**
 * 
 * Language not found error for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class LanguageNotFound extends Exception {
    public final static String MESSAGE = "Language not found";

    public LanguageNotFound () {
        super(MESSAGE);
    }
}
