package model.instruction.multiturtle;

import java.util.Collection;
import java.util.List;
import model.Model;
import model.Turtle;
import model.instruction.ComplexParameterInstruction;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;
import model.instruction.InstructionListNode;


public class InstructionASK extends Instruction implements ComplexParameterInstruction {

    public InstructionASK (Instruction parent, Model m) {
        super(Integer.MAX_VALUE, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        // save original active turtle IDs
        Collection<Integer> originalActiveIDs = getModel().getActiveTurtleIDs();

        for (int id : originalActiveIDs) {
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

    @Override
    public int getNumExpressions () {
        return 0;
    }

    @Override
    public int getNumLists () {
        return 2;
    }

    @Override
    public int getNumWords () {
        return 0;
    }

    @Override
    public void processParameters (List<String> params) throws Exception {
        String idList = params.get(0);
        String commands = params.get(1);
        List<Instruction> parameters = getModel().getInterpreter().getInstructions(idList);
        for (Instruction child : parameters) {
            addChild(child);
        }
        InstructionListNode instructionNode = new InstructionListNode(null, getModel(), commands);
        addChild(instructionNode);
    }
}
