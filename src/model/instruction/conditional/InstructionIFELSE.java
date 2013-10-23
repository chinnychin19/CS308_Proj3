package model.instruction.conditional;

import java.util.List;
import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;
import model.instruction.InstructionListNode;


/**
 * 
 * Ifelse instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionIFELSE extends InstructionConditional {

    public InstructionIFELSE (Instruction parent, Model m) {
        super(3, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        double condition = ((InstructionConstant) getChildren().get(0).eval()).getValue();
        int index = Math.abs(condition) > InstructionConditional.EPSILON ? 1 : 2;
        return (InstructionConstant) getChildren().get(index).eval();
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
        return 2;
    }

    @Override
    public void processParameters (List<String> params) throws Exception {
        String condition = params.get(0);
        String commandsIf = params.get(1);
        String commandsElse = params.get(2);
        List<Instruction> condList = getModel().getInterpreter().getInstructions(condition);
        InstructionListNode commListIf =
                new InstructionListNode(getParent(), getModel(), commandsIf);
        InstructionListNode commListElse =
                new InstructionListNode(getParent(), getModel(), commandsElse);
        addChild(condList.get(0));
        addChild(commListIf);
        addChild(commListElse);
    }
}
