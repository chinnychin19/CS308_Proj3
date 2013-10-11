package model.instruction.error;

public class NonPositiveRandom extends Exception {
    public static final String MESSAGE = "max of RANDOM must be strictly positive";

    public NonPositiveRandom () {
        super(MESSAGE);
    }
}
