package model.instruction.error;

public class TanOfNinety extends Exception {
    public static final String MESSAGE = "TAN of 90 degrees undefined";

    public TanOfNinety () {
        super(MESSAGE);
    }

}
