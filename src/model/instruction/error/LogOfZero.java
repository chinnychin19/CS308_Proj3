package model.instruction.error;

public class LogOfZero extends Exception {
    public static final String MESSAGE = "LOG of zero undefined";

    public LogOfZero () {
        super(MESSAGE);
    }
}
