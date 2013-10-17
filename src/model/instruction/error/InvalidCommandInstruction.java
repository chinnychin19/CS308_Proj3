package model.instruction.error;

import model.Model;


public class InvalidCommandInstruction extends ErrorInstruction {
    public static final String MESSAGE = "Invalid command";

    public InvalidCommandInstruction (Model m) {
        super(MESSAGE, m);
    }

}
