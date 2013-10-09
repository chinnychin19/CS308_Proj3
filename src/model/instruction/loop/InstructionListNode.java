package model.instruction.loop;

import model.instruction.Instruction;
import model.instruction.InstructionConstant;

public class InstructionListNode extends Instruction {

    public InstructionListNode (Instruction parent) {
        super(Integer.MAX_VALUE, parent);
    }

    @Override
    public Instruction eval () {
        Instruction lastChild = null;

        for (Instruction child : this.myChildren) {
            lastChild = child.eval();
        }

        return lastChild == null ? new InstructionConstant(0, null) : lastChild;
    }

}
