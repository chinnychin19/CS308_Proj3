package model.instruction;

import model.Model;


/**
 * 
 * Instruction variable class that keeps track of a variable
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionVariable extends InstructionConstant {
    private String myName;

    /**
     * Constructor for the instruction variable. Initializes the instruction using the abstract
     * method's constructor and initializes the string identifier to the passed in string
     * 
     * @param name The string to identify the variable
     * @param parent The direct parent of the instruction variable
     * @param m The model that the instruction belongs to
     */
    public InstructionVariable (String name, Instruction parent, Model m) {
        super(m.getVariableCache().get(name), parent, m);
        myName = name;
    }

    /**
     * @return The value of the given variable from the variable cache
     */
    @Override
    public double getValue () {
        return getModel().getVariableCache().get(myName);
    }

    /**
     * @return An instance of an instruction variable with the current variable name and value
     */
    @Override
    public Instruction eval () throws Exception {
        return new InstructionConstant(getModel().getVariableCache().get(myName), null, getModel());
    }

    /**
     * @return The name of the variable
     */
    public String getName () {
        return myName;
    }

}
