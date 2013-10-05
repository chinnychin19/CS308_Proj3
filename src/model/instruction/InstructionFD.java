package model.instruction;

public class InstructionFD extends Instruction {

    public InstructionFD () {
        super(1); // FD takes 1 parameter
    }

    @Override
    protected Instruction eval () {
        return getChildren().get(0).eval();
    }
}
