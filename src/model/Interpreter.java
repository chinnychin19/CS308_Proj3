package model;

import java.util.ArrayList;
import java.util.List;
import model.instruction.*;
import model.instruction.loop.InstructionLoop;
import model.instruction.loop.InstructionREPEAT;


public class Interpreter {

    protected void parseInput (String input) {
        input = input.replaceAll("\\s+", " "); // all white space becames a ' ' (space character)
        input = input.trim();
        if (input.isEmpty()) { return; }
        Model.getCommandHistory().add(input);
        List<Instruction> instructions = getInstructions(input);
        for (Instruction instr : instructions) {
            Model.getInstructionQueue().add(instr);
        }
    }

    public List<Instruction> getInstructions (String input) {
        Parser parser = new Parser(input);
        List<Instruction> instructions = new ArrayList<Instruction>();
        Instruction root = InstructionFactory.getInstruction(parser.nextWord(), null);
        Instruction cur = root;
        while (parser.hasNext()) {
            if (cur.getChildren().size() < cur.getNumParams()) {
                if (cur instanceof InstructionLoop) {
                    if (cur instanceof InstructionREPEAT) {
                        String parameters = parser.nextExpression(); // not enclosed in brackets
                        ((InstructionLoop) cur).setParameters(parameters);
                    }
                    else {
                        String parameters = parser.nextList(); // should be enclosed in brackets
                        ((InstructionLoop) cur).setParameters(parameters);
                    }
                    String commandsInLoop = parser.nextList();
                    commandsInLoop =
                            commandsInLoop.substring(1, commandsInLoop.length() - 1).trim();
                    // chop of brackets
                    List<Instruction> listCommands = getInstructions(commandsInLoop);
                    InstructionListNode node = new InstructionListNode(cur);
                    for (Instruction instr : listCommands) {
                        node.addChild(instr); // add all instructions to the list
                    }
                    cur.addChild(node);
                }
                else { // Normal instruction
                    Instruction temp = InstructionFactory.getInstruction(parser.nextWord(), cur);
                    cur.addChild(temp);
                    cur = temp;
                }
            }
            else {
                cur = cur.getParent();
            }
            if (cur == null) { // backtracked up to root's parent
                instructions.add(root);
                if (parser.hasNext()) {
                    root = InstructionFactory.getInstruction(parser.nextWord(), null);
                    cur = root;
                }
                else {
                    return instructions;
                }
            }
        }
        instructions.add(root);
        return instructions;
    }
}
