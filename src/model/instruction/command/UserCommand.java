package model.instruction.command;

import java.util.ArrayList;
import java.util.List;
import model.Model;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;
import model.instruction.InstructionListNode;


/**
 * 
 * User command instruction that keeps track of a user-defined command. Keeps track of the parameter
 * names, the name of the command the string of commands to operate, and the root command to operate
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class UserCommand extends Instruction {
    private List<String> myParamNames;
    private String myName;
    private String myCommandsString; // does not include brackets
    private InstructionListNode myRootCommand;

    /**
     * Constructor for the user command class. Calls the abstract class's constructor and
     * initializes the name of the command to the passed in string, the parameter names to the
     * passed in names, the command body to the passed in body, and the root command to the passed
     * in command
     * 
     * @param parent The direct parent of the current instruction
     * @param name The name of the user-defined command
     * @param paramNames The parameter names for the command
     * @param commandBody The body of the user-defined command
     * @param m The model that the instruction belongs to
     * @throws Exception An error if an error occurs
     */
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

    /**
     * @return The parameter names of the command
     */
    public List<String> getParamNames () {
        return myParamNames;
    }

    /**
     * @return Returns the number of parameters that the command needs to be passed in
     */
    @Override
    public int getNumParams () {
        return myParamNames.size() + 1;
        // add one because InstructionListNode is permanently stored as first child
    }

    /**
     * Evaluates the user-defined command using the passed in values
     * 
     * @return The value of the command after it has been evaluated
     * @throws Exception An error if an error occurs
     */
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

    /**
     * @return The root command of the command
     */
    public InstructionListNode getRootNode () {
        return myRootCommand;
    }

    /**
     * @return The body of the user-defined command
     */
    public String getBody () {
        return "TO " + myName + " \n[ " + myParamNames.toString().replaceAll(",", "") + " ] \n" +
               "[ " + myCommandsString + " ]";
    }

    /**
     * @return A copy of the current command
     */
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
