package model.instruction.multiturtle;

import java.util.List;
import model.Model;
import model.Turtle;
import model.instruction.ComplexParameterInstruction;
import model.instruction.Instruction;
import model.instruction.InstructionConstant;


public class InstructionTELL extends Instruction implements ComplexParameterInstruction {

    public InstructionTELL (Instruction parent, Model m) {
        super(Integer.MAX_VALUE, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        int id = 0;
        for (Turtle t : getModel().getActiveTurtles()) {
            t.setActive(false);
        }
        for (Instruction child : getChildren()) {
            id = (int) Math.round(((InstructionConstant) child.eval()).getValue());
            getModel().getTurtle(id).setActive(true);
        }
        return new InstructionConstant(id, null, getModel());
    }

    @Override
    public int getNumExpressions () {
        return 0;
    }

    @Override
    public int getNumLists () {
        return 1;
    }

    @Override
    public int getNumWords () {
        return 0;
    }

    @Override
    public void processParameters (List<String> params) throws Exception {
        String idList = params.get(0);
        List<Instruction> parameters = getModel().getInterpreter().getInstructions(idList);
        for (Instruction child : parameters) {
            addChild(child);
        }
    }

}
