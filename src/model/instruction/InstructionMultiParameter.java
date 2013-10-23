package model.instruction;

import java.util.List;
import model.Model;


/**
 * 
 * Multiparameter instruction for SLogo. Evaluates commands with multiple parameters
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionMultiParameter extends Instruction {

    public InstructionMultiParameter (String expression, Instruction parent, Model m)
                                                                                     throws Exception {
        super(-1, parent, m); // all children assigned in constructor, so no new children should be
                              // added
        expression = expression.substring(1, expression.length() - 1).trim();
        String[] tokens = expression.split("\\s");
        String commandString = tokens[0];
        String paramString = expression.substring(commandString.length()).trim();

        Instruction command = getModel().getInstructionFactory()
                .getInstruction(commandString, null);
        List<Instruction> parameters = getModel().getInterpreter().getInstructions(paramString);
        addChild(command); // command is first child
        for (Instruction child : parameters) {
            addChild(child);
        }
    }

    @Override
    public Instruction eval () throws Exception {
        Instruction command = getChildren().get(0);
        if (command.getNumParams() != 2) { throw new Exception(
                                                               "Multiple parameter expressions may only be applied to commands which take 2 parameters."); }
        while (getChildren().size() != 2) { // command and a single remaining parameter
            Instruction a = getChildren().remove(1).eval();
            Instruction b = getChildren().remove(1).eval();
            command.getChildren().clear();
            command.addChild(a);
            command.addChild(b);
            getChildren().add(1, command.eval());
        }
        return getChildren().get(1);
    }

}
