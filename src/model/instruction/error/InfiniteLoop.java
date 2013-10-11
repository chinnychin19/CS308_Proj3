package model.instruction.error;

public class InfiniteLoop extends Exception {
    public final static String MESSAGE = "Entered an infinite loop";

    public InfiniteLoop () {
        super(MESSAGE);
    }
}
