package model.instruction.loop;

import java.util.List;
import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;
import model.instruction.InstructionVariable;
import model.instruction.loop.InstructionLoop;


public class InstructionDOTIMES extends InstructionLoop {

    public InstructionDOTIMES (Instruction parent) {
        super(parent);
    }

    @Override
    public void setParameters (String parameters) {
        parameters = parameters.trim(); // chop off potential white space
        parameters = parameters.substring(1, parameters.length() - 1);// chop off brackets
        List<Instruction> paramNodes = Model.getInterpreter().getInstructions(parameters);
        // Parameters: variable, limit (end + 1)
        setVariable(((InstructionVariable) paramNodes.get(0)).getName());
        setEnd(((InstructionConstant) paramNodes.get(1)).getValue() - 1);
    }

}