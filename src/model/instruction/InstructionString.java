package model.instruction;

import model.Model;


public class InstructionString extends Instruction {
    private String myString;

    public InstructionString (String str, Instruction parent, Model m) {
        super(0, parent, m);
        myString = str;
    }

    @Override
    public Instruction eval () throws Exception {
        return new InstructionString(myString, null, getModel());
    }

    public String getString () {
        return myString;
    }

}
