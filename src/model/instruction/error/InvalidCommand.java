package model.instruction.error;

public class InvalidCommand extends Error {

    public InvalidCommand () {
        super("Command not found!", null);
    }

}
