package model.instruction.error;

public class InvalidCommandInstruction extends ErrorInstruction {
    public static final String MESSAGE = "Invalid command";
    public InvalidCommandInstruction () {
        super(MESSAGE);
    }

}
