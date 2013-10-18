package model.instruction.error;

public class FileNotFound extends Exception {
    public static final String MESSAGE = "File was not found";

    public FileNotFound () {
        super(MESSAGE);
    }
}
