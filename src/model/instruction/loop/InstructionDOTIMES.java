package model.instruction.loop;

import java.util.List;
import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;
import model.instruction.InstructionListNode;
import model.instruction.InstructionVariable;
import model.instruction.loop.InstructionLoop;


/**
 * 
 * Dotimes instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionDOTIMES extends InstructionLoop {

    public InstructionDOTIMES (Instruction parent, Model m) {
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
            // Parameters: variable, limit (end + 1)
            setVariable(((InstructionVariable) paramNodes.get(0)).getName());
            setEnd(((InstructionConstant) paramNodes.get(1)).getValue() - 1);
            setStart(0); // hard coded for DOTIMES
            setIncrement(1); // hard coded for DOTIMES
            InstructionListNode instructionNode =
                    new InstructionListNode(null, getModel(), commands);
            addChild(instructionNode);
        }
        catch (Exception e) {
            throw new Exception("Invalid parameters for DOTIMES");
        }
    }

}
