package model.instruction.loop;

import java.util.List;
import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;
import model.instruction.InstructionListNode;
import model.instruction.InstructionVariable;


public class InstructionFOR extends InstructionLoop {

    public InstructionFOR (Instruction parent, Model m) {
        super(parent, m);
    }

    @Override
    public int getNumWords () {
        return 0;
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
    public void processParameters (List<String> params) throws Exception {
        try {
            String parameters = params.get(0);
            String commands = params.get(1);
            List<Instruction> paramNodes = getModel().getInterpreter().getInstructions(parameters);
            // Parameters: variable, start, end, increment
            setVariable(((InstructionVariable) paramNodes.get(0)).getName());
            setStart(((InstructionConstant) paramNodes.get(1)).getValue());
            setEnd(((InstructionConstant) paramNodes.get(2)).getValue());
            setIncrement(((InstructionConstant) paramNodes.get(3)).getValue());
            InstructionListNode instructionNode =
                    new InstructionListNode(null, getModel(), commands);
            addChild(instructionNode);
        }
        catch (Exception e) {
            throw new Exception("Invalid parameters for FOR");
        }
    }

}
