package model.instruction;

import java.util.ArrayList;
import java.util.List;
import model.Model;
import model.instruction.command.UserCommand;


public class InstructionTO extends Instruction implements ComplexParameterInstruction {

    public InstructionTO (Instruction parent, Model m) {
        super(3, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        String name = ((InstructionString) getChildren().get(0)).getString().trim();
        String params = ((InstructionString) getChildren().get(1)).getString().trim();
        params = params.substring(1, params.length() - 1).trim(); // chop off brackets
        String[] pNames = params.trim().isEmpty() ? new String[0] : params.split("\\s");
        List<String> pList = new ArrayList<String>();
        for (String param : pNames) {
            pList.add(param);
        }
        String commands = ((InstructionString) getChildren().get(2)).getString().trim();
        UserCommand newCommand = new UserCommand(null, name, pList, commands, getModel());
        getModel().getCommandCache().put(name, newCommand);
        return new InstructionConstant(1, null, getModel());
    }

    @Override
    public int getNumExpressions () {
        return 0;
    }

    @Override
    public int getNumLists () {
        return 2;
    }

    @Override
    public int getNumWords () {
        return 1; // this is the only time this is non-zero
    }

}
