package model.instruction.error;

import model.instruction.Instruction;


public class ErrorInstruction extends Instruction {

    private String myMessage;

    public ErrorInstruction (String message) {
        super(0, null);
        myMessage = message;
    }

    @Override
    public Instruction eval () throws Exception {
        throw new Exception(myMessage);
    }
}
