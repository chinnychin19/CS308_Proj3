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
        Scanner sc = new Scanner(input);
        Instruction root = InstructionFactory.getInstruction(sc.next(), null);
        Instruction cur = root;
        while (sc.hasNext()) {
            if (cur.getChildren().size() < cur.getNumParams()) {
                Instruction temp = InstructionFactory.getInstruction(sc.next(), cur);
                cur.addChild(temp);
                cur = temp;
            }
            else {
                cur = cur.getParent(); // assumes parent exists, could be an error
            }
            if (cur == null) {
                Model.getInstructionQueue().add(root);
                if (sc.hasNext()) {
                    root = InstructionFactory.getInstruction(sc.next(), null);
                    cur = root;
                }
            }
        }
        sc.close();
        Model.getInstructionQueue().add(root);
    }
}
