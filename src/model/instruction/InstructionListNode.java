package model.instruction;

import java.util.List;
import model.Model;


public class InstructionListNode extends Instruction {

    // public InstructionListNode (Instruction parent, Model m) {
    // super(Integer.MAX_VALUE, parent, m);
    // }

    public InstructionListNode (Instruction parent, Model m, String instructions) throws Exception {
        super(Integer.MAX_VALUE, parent, m);
        List<Instruction> listCommands = getModel().getInterpreter().getInstructions(instructions);
        for (Instruction instr : listCommands) {
            addChild(instr); // add all instructions to the list
        }
    }

    @Override
    public Instruction eval () throws Exception {
        Instruction lastChild = null;

        for (Instruction child : getChildren()) {
            lastChild = child.eval();
        }

        return lastChild == null ? new InstructionConstant(0, null, getModel()) : lastChild;
    }

}
