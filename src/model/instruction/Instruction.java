package model.instruction;

import java.util.ArrayList;
import java.util.List;
import model.Model;


/**
 * 
 * Abstract instruction class that all instructions extend. Keeps track of the direct children of
 * the instruction in the tree, the number of parameters the instruction expects, the direct parent
 * of the
 * current instruction in the tree, and model that the instruction instance belongs to
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public abstract class Instruction {
    private List<Instruction> myChildren;
    private int myParamCount;
    private Instruction myParent;
    private Model myModel;

    /**
     * Constructor for an instruction which initializes the parameters expected, the direct children
     * of the instruction, the direct parent of the instruction, and the model that the instruction
     * belongs to
     * 
     * @param paramCount The number of parameters that the instruction expects
     * @param parent The direct parent of the current instruction
     * @param m The model that the instruction belongs to
     */
    public Instruction (int paramCount, Instruction parent, Model m) {
        myParamCount = paramCount;
        myChildren = new ArrayList<Instruction>();
        myParent = parent;
        myModel = m;
    }

    /**
     * @return The model that the instruction belongs to
     */
    public Model getModel () {
        return myModel;
    }

    /**
     * @return The number of parameters that the instruction expects
     */
    public int getNumParams () {
        return myParamCount;
    }

    /**
     * @return The direct parent of the instruction
     */
    public Instruction getParent () {
        return myParent;
    }

    /**
     * @return The direct children of the instruction
     */
    public List<Instruction> getChildren () {
        return myChildren;
    }

    /**
     * Adds a child to the instruction in the tree
     * 
     * @param child The child instruction to add to the current instruction
     */
    public void addChild (Instruction child) {
        myChildren.add(child);
    }

    /**
     * Abstract evaluation method for each instruction. Returns back the value of evaluating the
     * instruction
     * 
     * @return The value of the evaluated instruction
     * @throws Exception An error if an error occurs
     */
    public abstract Instruction eval () throws Exception;
}
