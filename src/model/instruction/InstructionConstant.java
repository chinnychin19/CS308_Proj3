package model.instruction;

import model.Model;


/**
 * 
 * Instruction constant class that evaluates to a constant number. Keeps track of the value
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionConstant extends Instruction {
    private double myValue;

    /**
     * Constructor for the instruction constant. Initializes the instruction using the abstract
     * method's constructor and initializes the value to the passed in value
     * 
     * @param value The value to set the constant to
     * @param parent The direct parent of the current instruction constant
     * @param m The model that the instruction constant belongs to
     */
    public InstructionConstant (double value, Instruction parent, Model m) {
        super(0, parent, m);
        myValue = value;
    }

    /**
     * @return An instance of an instruction constant with the current number value
     * @throws Exception An error if an error occurs
     */
    @Override
    public Instruction eval () throws Exception {
        return new InstructionConstant(myValue, null, getModel());
    }

    /**
     * @return The number value of the instruction constant
     */
    public double getValue () {
        return myValue;
    }
}
