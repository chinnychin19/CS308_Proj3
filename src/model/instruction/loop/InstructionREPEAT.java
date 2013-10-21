package model.instruction.loop;

import java.util.List;
import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;
import model.instruction.error.ErrorInstruction;


public class InstructionREPEAT extends InstructionLoop {
    private boolean hasBadParamaters;

    public InstructionREPEAT (Instruction parent, Model m) {
        super(parent, m);
        hasBadParamaters = false;
    }

    @Override
    public void setParameters (String parameters) {
        try {
            // parameters will not be in brackets, it will just be an expression
            parameters = parameters.trim(); // chop off potential white space
            List<Instruction> paramNodes = getModel().getInterpreter().getInstructions(parameters);
            // Parameters: variable, limit (end + 1)
            setEnd(((InstructionConstant) paramNodes.get(0).eval()).getValue());
            setStart(1); // hard coded for REPEAT
            setIncrement(1); // hard coded for REPEAT
            setVariable(":repcount");// hard coded for REPEAT
        }
        catch (Exception e) {
            hasBadParamaters = true;
        }
    }

    @Override
    public Instruction eval () throws Exception {
        if (hasBadParamaters) { return new ErrorInstruction("REPEAT loop had bad parameters",
                                                            getModel()); }
        return super.eval();
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

}
