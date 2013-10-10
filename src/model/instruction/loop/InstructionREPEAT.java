package model.instruction.loop;

import java.util.List;
import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionREPEAT extends InstructionLoop {

    public InstructionREPEAT (Instruction parent) {
        super(parent);
    }

    @Override
    public void setParameters (String parameters) {
        // parameters will not be in brackets, it will just be an expression
        parameters = parameters.trim(); // chop off potential white space
        List<Instruction> paramNodes = Model.getInterpreter().getInstructions(parameters);
        // Parameters: variable, limit (end + 1)
        setEnd(((InstructionConstant) paramNodes.get(0).eval()).getValue());
        setStart(1); // hard coded for REPEAT
        setIncrement(1); // hard coded for REPEAT
        setVariable(":repcount");// hard coded for REPEAT
    }

}
