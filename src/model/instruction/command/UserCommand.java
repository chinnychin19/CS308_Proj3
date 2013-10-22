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
    private String myCommandsString; // does not include brackets
    private InstructionListNode myRootCommand;

    public UserCommand (Instruction parent,
                        String name,
                        List<String> paramNames,
                        String commandBody, Model m) throws Exception {
        super(paramNames.size(), parent, m);
        myName = name;
        myParamNames = paramNames;
        myCommandsString = commandBody;
        myRootCommand = new InstructionListNode(null, getModel(), commandBody);
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
        // set parameter values and save old ones
        for (int i = 1; i <= myParamNames.size(); i++) {
            double oldValue = getModel().getVariableCache().get(myParamNames.get(i - 1));
            double newValue = ((InstructionConstant) getChildren().get(i)).getValue();
            oldParameterValues.add(oldValue);
            getModel().getVariableCache().put(myParamNames.get(i - 1), newValue);
        }
        Instruction ret = myRootCommand.eval();
        // reset old parameter values
        for (int i = 0; i < myParamNames.size(); i++) {
            getModel().getVariableCache().put(myParamNames.get(i), oldParameterValues.get(i));
        }
        return ret;
    }

    public InstructionListNode getRootNode () {
        return myRootCommand;
    }

    public String getBody () {
        return "TO " + myName + " \n[ " + myParamNames.toString().replaceAll(",", "") + " ] \n" +
               "[ " + myCommandsString + " ]";
    }

    public UserCommand copy () {
        try {
            List<String> paramNamesCopy = new ArrayList<String>(myParamNames);
            return new UserCommand(getParent(), myName, paramNamesCopy, myCommandsString,
                                   getModel());
        }
        catch (Exception e) {
            return null;
        }
    }
}
