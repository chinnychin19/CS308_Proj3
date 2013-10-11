package model.instruction;

public class InstructionString extends Instruction {
    private String myString;

    public InstructionString (String str, Instruction parent) {
        super(0, parent);
        myString = str;
    }

    @Override
    public Instruction eval () {
        return new InstructionString(myString, null);
    }

    public String getString () {
        return myString;
    }

}
