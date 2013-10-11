package model.instruction.error;

public class InternalError extends Exception {
    public static final String MESSAGE = "Oops! Something went wrong. We messed up. Our bad.";

    public InternalError () {
        super(MESSAGE);
    }
}
