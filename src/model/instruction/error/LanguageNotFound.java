package model.instruction.error;

public class LanguageNotFound extends Exception {
    public final static String MESSAGE = "Language not found";

    public LanguageNotFound () {
        super(MESSAGE);
    }
}
