package model.instruction.command;

import java.util.ArrayList;
import java.util.List;
import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;
import model.instruction.InstructionListNode;


public class UserCommand extends Instruction {
    private List<String> myParamNames;
    private String myName;
    private String myDefinitionString;
    private InstructionListNode myRootCommand;

    public UserCommand (Instruction parent,
                        String name,
                        List<String> paramNames,
                        String commandBody) {
        super(paramNames.size(), parent);
        myParamNames = paramNames;
        myDefinitionString = commandBody;
        commandBody = commandBody.substring(1, commandBody.length() - 1).trim();
        // chop off brackets
        List<Instruction> commandList = Model.getInterpreter().getInstructions(commandBody);
        myRootCommand = new InstructionListNode(null);
        for (Instruction instr : commandList) {
            myRootCommand.addChild(instr);
        }
        myName = name;
    }

    @Override
    public int getNumParams () {
        return myParamNames.size() + 1;
        // add one because InstructionListNode is permanently stored as first child
    }

    @Override
    public Instruction eval () {
        List<Double> oldParameterValues = new ArrayList<Double>();
        // set parameter values
        for (int i = 1; i <= myParamNames.size(); i++) {
            double value = ((InstructionConstant) getChildren().get(i)).getValue();
            oldParameterValues.add(value);
            Model.getVariableCache().put(myParamNames.get(i - 1), value);
        }
        Instruction ret = myRootCommand.eval();
        // reset old parameter values
        for (int i = 0; i < myParamNames.size(); i++) {
            Model.getVariableCache().put(myParamNames.get(i), oldParameterValues.get(i));
        }
        return ret;
    }

    public InstructionListNode getRootNode () {
        return myRootCommand;
    }

    public String getBody () {
        return "TO " + myName + " \n" + myParamNames.toString() + " \n" + myDefinitionString;
    }
}
