package model.instruction.error;

import model.Model;


public class InvalidCommand extends Exception {
    public static final String MESSAGE = "Invalid command entered";

    public InvalidCommand () {
        super(MESSAGE);
    }

}
