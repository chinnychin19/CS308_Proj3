package model.instruction.conditional;

import java.util.List;
import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;
import model.instruction.InstructionListNode;


/**
 * 
 * If instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionIF extends InstructionConditional {
    public InstructionIF (Instruction parent, Model m) {
        super(2, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        double condition = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        if (Math.abs(condition) > InstructionConditional.EPSILON) {
            return (InstructionConstant) getChildren().get(1)
                    .eval();
        }
        else {
            return new InstructionConstant(0, null, getModel());
        }
    }

    @Override
    public int getNumWords () {
        return 0;
    }

    @Override
    public int getNumExpressions () {
        return 1;
    }

    @Override
    public int getNumLists () {
        return 1;
    }

    @Override
    public void processParameters (List<String> params) throws Exception {
        String condition = params.get(0);
        String commands = params.get(1);
        List<Instruction> condList = getModel().getInterpreter().getInstructions(condition);
        InstructionListNode commList = new InstructionListNode(getParent(), getModel(), commands);
        addChild(condList.get(0));
        addChild(commList);
    }
}
