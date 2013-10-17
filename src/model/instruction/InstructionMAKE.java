package model.instruction;

import model.Model;


public class InstructionMAKE extends Instruction {

    public InstructionMAKE (Instruction parent, Model m) {
        super(2, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        String name = ((InstructionVariable) getChildren().get(0)).getName();
        double value = ((InstructionConstant) getChildren().get(1).eval()).getValue();
        getModel().getVariableCache().put(name, value);
        return new InstructionConstant(value, null, getModel());
    }

}
