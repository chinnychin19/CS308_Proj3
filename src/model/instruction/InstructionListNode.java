package model.instruction;

import model.Model;


public class InstructionListNode extends Instruction {

    public InstructionListNode (Instruction parent, Model m) {
        super(Integer.MAX_VALUE, parent, m);
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
