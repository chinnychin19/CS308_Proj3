package model.instruction.multiturtle;

import java.util.Collection;
import model.Model;
import model.Turtle;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;
import model.instruction.InstructionListNode;


public class InstructionASK extends Instruction {

    public InstructionASK (Instruction parent, Model m) {
        super(Integer.MAX_VALUE, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        Collection<Integer> originalActiveIDs = getModel().getActiveTurtleIDs();

        for (int id : originalActiveIDs) { // save original active turtle IDs
            getModel().getTurtle(id).setActive(false);
        }

        for (int i = 0; i < getChildren().size() - 1; i++) { // last child is a list node of
                                                             // commands
            Instruction child = getChildren().get(i);
            int id = (int) Math.round(((InstructionConstant) child.eval()).getValue());
            getModel().getTurtle(id).setActive(true);
        }
        InstructionListNode commands =
                (InstructionListNode) getChildren().get(getChildren().size() - 1);
        InstructionConstant ret = (InstructionConstant) commands.eval();

        // reset original active IDs
        for (int id : getModel().getAllTurtleIDs()) {
            if (originalActiveIDs.contains(id)) {
                getModel().getTurtle(id).setActive(true);
            }
            else {
                getModel().getTurtle(id).setActive(false);
            }
        }

        return new InstructionConstant(ret.getValue(), null, getModel());
    }
}
