package model.instruction.error;

import model.Model;
import model.instruction.Instruction;


public class ErrorInstruction extends Instruction {

    private String myMessage;

    public ErrorInstruction (String message, Model m) {
        super(0, null, m);
        myMessage = message;
    }

    @Override
    public Instruction eval () throws Exception {
        throw new Exception(myMessage);
    }
}
