package model.instruction.error;

public class ColorNotFound extends Exception {
    public static final String MESSAGE = "Color is no longer in palette";

    public ColorNotFound () {
        super(MESSAGE);
    }
}
