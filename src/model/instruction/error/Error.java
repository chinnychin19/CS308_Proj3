package model.instruction.error;

import model.instruction.Instruction;

public abstract class Error extends Instruction {
    
    private String myMessage;

    public Error (String message, Instruction parent) {
        super(0, parent);
        myMessage = message;
    }

    public String getMessage () {
        return myMessage;
    }
    
    @Override
    public Instruction eval() {
        return this;
    }
}
