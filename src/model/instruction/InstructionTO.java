package model.instruction;

import java.util.ArrayList;
import java.util.List;
import model.Model;
import model.instruction.command.UserCommand;


public class InstructionTO extends Instruction {

    public InstructionTO (Instruction parent) {
        super(3, parent);
    }

    @Override
    public Instruction eval () {
        String name = ((InstructionString) getChildren().get(0)).getString().trim();
        String params = ((InstructionString) getChildren().get(1)).getString().trim();
        params = params.substring(1, params.length() - 1).trim(); // chop off brackets
        String[] pNames = params.split("\\s");
        List<String> pList = new ArrayList<String>();
        for (String param : pNames) {
            pList.add(param);
        }
        String commands = ((InstructionString) getChildren().get(2)).getString().trim();
        UserCommand newCommand = new UserCommand(null, name, pList, commands);
        Model.getCommandCache().put(name, newCommand);
        return new InstructionConstant(1, null);
    }

}
