package model.instruction.loop;

import java.util.List;
import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;
import model.instruction.InstructionListNode;


/**
 * 
 * Repeat instruction for SLogo
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionREPEAT extends InstructionLoop {
    public InstructionREPEAT (Instruction parent, Model m) {
        super(parent, m);
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
        try {
            String parameters = params.get(0);
            String commands = params.get(1);
            List<Instruction> paramNodes = getModel().getInterpreter().getInstructions(parameters);
            // Parameter: end
            setEnd(((InstructionConstant) paramNodes.get(0).eval()).getValue());
            setStart(1); // hard coded for REPEAT
            setIncrement(1); // hard coded for REPEAT
            setVariable(":repcount");// hard coded for REPEAT
            InstructionListNode instructionNode =
                    new InstructionListNode(null, getModel(), commands);
            addChild(instructionNode);
        }
        catch (Exception e) {
            throw new Exception("Invalid parameters for REPEAT");
        }
    }
}
