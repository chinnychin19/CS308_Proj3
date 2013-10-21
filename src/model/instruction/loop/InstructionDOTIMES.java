package model.instruction.loop;

import java.util.List;
import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;
import model.instruction.InstructionVariable;
import model.instruction.error.TooFewParametersInstruction;
import model.instruction.loop.InstructionLoop;


public class InstructionDOTIMES extends InstructionLoop {

    public InstructionDOTIMES (Instruction parent, Model m) {
        super(parent, m);
    }

    @Override
    public void setParameters (String parameters) throws Exception {
        parameters = parameters.trim(); // chop off potential white space
        parameters = parameters.substring(1, parameters.length() - 1);// chop off brackets
        List<Instruction> paramNodes = null;
        paramNodes = getModel().getInterpreter().getInstructions(parameters);
        try {
            // Parameters: variable, limit (end + 1)
            setVariable(((InstructionVariable) paramNodes.get(0)).getName());
            setEnd(((InstructionConstant) paramNodes.get(1)).getValue() - 1);
            setStart(0); // hard coded for DOTIMES
            setIncrement(1); // hard coded for DOTIMES
        }
        catch (Exception e) {
            throw new Exception("Invalid parameters for DOTIMES");
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
