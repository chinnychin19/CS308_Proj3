package model.instruction;

import java.util.List;


/**
 * 
 * Interface for instructions that include many different parts (words, expressions, and lists).
 * Stores the number of words, expressions, and lists that the complex instruction expects and a
 * method to process the parameters passed in
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public interface ComplexParameterInstruction {
    public int getNumWords (); // only used in the TO command

    public int getNumExpressions ();

    public int getNumLists ();

    /**
     * Processes the parameters that are passed into the instruction
     * 
     * @param params The parameters for the instruction
     * @throws Exception An error message if an error occurs
     */
    // params will never be encased in brackets
    public void processParameters (List<String> params) throws Exception;
}
