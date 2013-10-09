package model.instruction.loop;

import java.util.List;
import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;
import model.instruction.InstructionVariable;


public class InstructionFOR extends InstructionLoop {

    public InstructionFOR (Instruction parent) {
        super(parent);
    }

    @Override
    public void setParameters (String parameters) {
        parameters = parameters.trim(); // chop off potential white space
        parameters = parameters.substring(1, parameters.length() - 1);// chop off brackets
        List<Instruction> paramNodes = Model.getInterpreter().getInstructions(parameters);
        // Parameters: variable, start, end, incremement
        setVariable(((InstructionVariable) paramNodes.get(0)).getName());
        setStart(((InstructionConstant) paramNodes.get(1)).getValue());
        setEnd(((InstructionConstant) paramNodes.get(2)).getValue());
        setIncrement(((InstructionConstant) paramNodes.get(3)).getValue());
    }

}
