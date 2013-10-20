package model.instruction.multiturtle;

import java.util.Collection;
import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;
import model.instruction.InstructionListNode;


public class InstructionASKWITH extends Instruction {

    public InstructionASKWITH (Instruction parent, Model m) {
        super(2, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        // save original active turtle IDs
        Collection<Integer> originalActiveIDs = getModel().getActiveTurtleIDs();

        for (int id : originalActiveIDs) {
            getModel().getTurtle(id).setActive(false);
        }

        double ret = 0;
        Instruction condition = getChildren().get(0);
        InstructionListNode commands = (InstructionListNode) getChildren().get(1);
        for (int id : getModel().getAllTurtleIDs()) {
            getModel().getTurtle(id).setActive(true);
            int boolValue = (int) Math.round(((InstructionConstant) condition.eval()).getValue());
            if (boolValue == 1) {
                ret = ((InstructionConstant) commands.eval()).getValue();
            }
            getModel().getTurtle(id).setActive(false);
        }

        // reset original active IDs
        for (int id : getModel().getAllTurtleIDs()) {
            if (originalActiveIDs.contains(id)) {
                getModel().getTurtle(id).setActive(true);
            }
            else {
                getModel().getTurtle(id).setActive(false);
            }
        }
        return new InstructionConstant(ret, null, getModel());
    }
}
