package model.instruction;

import model.Model;


public class InstructionMAKE extends Instruction {

    public InstructionMAKE (Instruction parent) {
        super(2, parent);
    }

    @Override
    public Instruction eval () throws Exception {
        String name = ((InstructionVariable) getChildren().get(0)).getName();
        double value = ((InstructionConstant) getChildren().get(1)).getValue();
        Model.getVariableCache().put(name, value);
        return new InstructionConstant(value, null);
    }

}
