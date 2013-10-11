package model.instruction.error;

public class DivideByZero extends Exception {
    public static final String MESSAGE = "/ by zero undefined";
    
    public DivideByZero() {
        super(MESSAGE);
    }
}
