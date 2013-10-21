package model.instruction;

public interface ComplexParameterInstruction {
    public int getNumWords (); // only used in the TO command

    public int getNumExpressions ();

    public int getNumLists ();
}
