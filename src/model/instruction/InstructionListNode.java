package model.instruction;

import java.util.List;
import model.Model;


/**
 * 
 * A node for a node of an instruction list
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionListNode extends Instruction {

    /**
     * Constructor for the instruction node. Calls the instruction's constructor and initializes a
     * list of commands to the instructions passed in
     * 
     * @param parent The direct parent of the instruction
     * @param m The model that the instruction belongs to
     * @param instructions The instructions to be created
     * @throws Exception An error if an error occurs
     */
    public InstructionListNode (Instruction parent, Model m, String instructions) throws Exception {
        super(Integer.MAX_VALUE, parent, m);
        List<Instruction> listCommands = getModel().getInterpreter().getInstructions(instructions);
        for (Instruction instr : listCommands) {
            addChild(instr); // add all instructions to the list
        }
    }

    /**
     * Evaluates the last term in the instruction list
     * 
     * @return The value of the last instruction in the list. If the last instruction is null,
     *         returns an instruction constant with value of 0
     * @throws Exception An error if an error occurs
     */
    @Override
    public Instruction eval () throws Exception {
        Instruction lastChild = null;

        for (Instruction child : getChildren()) {
            lastChild = child.eval();
        }

        return lastChild == null ? new InstructionConstant(0, null, getModel()) : lastChild;
    }

}
