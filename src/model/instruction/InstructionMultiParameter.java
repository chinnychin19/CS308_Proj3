package model.instruction;

import model.Model;


public class InstructionMultiParameter extends Instruction {

    public InstructionMultiParameter (Instruction parent, Model m) {
        super(Integer.MAX_VALUE, parent, m);
    }

    @Override
    public Instruction eval () throws Exception {
        Instruction command = getChildren().get(0);
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
