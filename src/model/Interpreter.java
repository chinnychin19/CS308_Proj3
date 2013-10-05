package model;

import java.util.Scanner;
import model.instruction.*;


public class Interpreter {
    private Model myModel;

    protected Interpreter (Model m) {
        // TODO
        myModel = m;
    }

    protected void parseInput (String input) {
        // TODO: update commandHistory
        myModel.getCommandHistory().add(input);
        Scanner sc = new Scanner(input);
        // TODO: scan and create Instruction tree using Instruction factory
    }
}
