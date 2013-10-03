package model.error;

import dataType.DataType;

public abstract class Error {
    private String myMessage;
    public Error(String message) {
        //TODO:super("message details") will be called in subclasses
        myMessage = message;
    }
    
    @Override
    public String toString() {
        return myMessage;
    }
}
