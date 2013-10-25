package model.instruction;

import model.Model;


/**
 * 
 * Instruction string class that evaluates to a string. Keeps track of the string
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionString extends Instruction {
    private String myString;

    /**
     * Constructor for the instruction string. Initializes the instruction using the abstract
     * method's constructor and initializes the string to the passed in string
     * 
     * @param str The string to initialize the instruction to
     * @param parent The direct parent of the instruction
     * @param m The model that the instruction belongs to
     */
    public InstructionString (String str, Instruction parent, Model m) {
        super(0, parent, m);
        myString = str;
    }

    /**
     * @return An instance of an instruction string with the current string value
     * @throws Exception An error if an error occurs
     */
    @Override
    public Instruction eval () throws Exception {
        return new InstructionString(myString, null, getModel());
    }

    /**
     * @return The string value of the current instruction string
     */
    public String getString () {
        return myString;
    }

}
