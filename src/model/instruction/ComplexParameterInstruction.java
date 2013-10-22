package model.instruction;

import java.util.List;


public interface ComplexParameterInstruction {
    public int getNumWords (); // only used in the TO command

    public int getNumExpressions ();

    public int getNumLists ();

    // params will never be encased in brackets
    public void processParameters (List<String> params) throws Exception;
}
