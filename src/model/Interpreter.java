package model;

import java.util.Scanner;
import model.instruction.*;


public class Interpreter {

    protected Interpreter () {
    }

    protected void parseInput (String input) {
        input = input.replaceAll("\\s+", " "); // all white space becames a ' ' (space character)
        input = input.trim();
        if (input.isEmpty()) { return; }
        Model.getCommandHistory().add(input);
        Parser parser = new Parser(input);
        Instruction root = InstructionFactory.getInstruction(parser.next(), null);
        Instruction cur = root;
        while (parser.hasNext()) {
            if (cur.getChildren().size() < cur.getNumParams()) {
                Instruction temp = InstructionFactory.getInstruction(parser.next(), cur);
                cur.addChild(temp);
                cur = temp;
            }
            else {
                cur = cur.getParent(); // assumes parent exists, could be an error
            }
            if (cur == null) {
                Model.getInstructionQueue().add(root);
                if (parser.hasNext()) {
                    root = InstructionFactory.getInstruction(parser.next(), null);
                    cur = root;
                }
            }
        }
        Model.getInstructionQueue().add(root);
    }
}
