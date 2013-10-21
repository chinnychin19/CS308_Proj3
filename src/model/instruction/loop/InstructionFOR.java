package model.instruction.loop;

import java.util.List;
import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;
import model.instruction.InstructionVariable;


public class InstructionFOR extends InstructionLoop {

    public InstructionFOR (Instruction parent, Model m) {
        super(parent, m);
    }

    @Override
    public void setParameters (String parameters) throws Exception {
        parameters = parameters.trim(); // chop off potential white space
        parameters = parameters.substring(1, parameters.length() - 1);// chop off brackets
        List<Instruction> paramNodes = getModel().getInterpreter().getInstructions(parameters);
        // Parameters: variable, start, end, incremement
        try {
            setVariable(((InstructionVariable) paramNodes.get(0)).getName());
            setStart(((InstructionConstant) paramNodes.get(1)).getValue());
            setEnd(((InstructionConstant) paramNodes.get(2)).getValue());
            setIncrement(((InstructionConstant) paramNodes.get(3)).getValue());
        }
        catch (Exception e) {
            throw new Exception("Invalid parameters for FOR");
        }
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

}
