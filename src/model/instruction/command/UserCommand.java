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
    private String myDefinitionString; // includes brackets
    private InstructionListNode myRootCommand;

    public UserCommand (Instruction parent,
                        String name,
                        List<String> paramNames,
                        String commandBody, Model m) throws Exception {
        super(paramNames.size(), parent, m);
        myName = name;
        myParamNames = paramNames;
        myDefinitionString = commandBody;
        commandBody = commandBody.substring(1, commandBody.length() - 1).trim();
        // chop off brackets
        List<Instruction> commandList = null;
        try {
            commandList = getModel().getInterpreter().getInstructions(commandBody);
        }
        catch (Exception e) {
            throw new Exception("User defined commands could not be interpreted.");
        }
        myRootCommand = new InstructionListNode(null, getModel());
        for (Instruction instr : commandList) {
            myRootCommand.addChild(instr);
        }
    }

    public List<String> getParamNames () {
        return myParamNames;
    }

    @Override
    public int getNumParams () {
        return myParamNames.size() + 1;
        // add one because InstructionListNode is permanently stored as first child
    }

    @Override
    public Instruction eval () throws Exception {
        List<Double> oldParameterValues = new ArrayList<Double>();
        // // set parameter values
        // for (int i = 1; i <= myParamNames.size(); i++) {
        // double value = ((InstructionConstant) getChildren().get(i)).getValue();
        // oldParameterValues.add(value);
        // Model.getVariableCache().put(myParamNames.get(i - 1), value);
        // }
        Instruction ret = myRootCommand.eval();
        // // reset old parameter values
        // for (int i = 0; i < myParamNames.size(); i++) {
        // Model.getVariableCache().put(myParamNames.get(i), oldParameterValues.get(i));
        // }
        return ret;
    }

    public InstructionListNode getRootNode () {
        return myRootCommand;
    }

    public String getBody () {
        return "TO " + myName + " \n[ " + myParamNames.toString().replaceAll(",", "") + " ] \n" +
               myDefinitionString;
    }
}
