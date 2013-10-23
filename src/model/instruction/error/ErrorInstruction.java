package model.instruction.error;

import model.Model;
import model.instruction.Instruction;


/**
 * 
 * The error instruction class that keeps track of an error message. Returns back the error message
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
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
