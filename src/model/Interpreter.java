package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import model.instruction.*;
import model.instruction.loop.InstructionListNode;
import model.instruction.loop.InstructionLoop;


public class Interpreter {

    protected Interpreter () {
    }

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
    
    public List<Instruction> getInstructions(String input) {
        Parser parser = new Parser(input);
        List<Instruction> instructions = new ArrayList<Instruction>();
        Instruction root = InstructionFactory.getInstruction(parser.next(), null);
        Instruction cur = root;
        while (parser.hasNext()) {
            if (cur.getChildren().size() < cur.getNumParams()) {
                if (cur instanceof InstructionLoop) {
                    ((InstructionLoop)cur).setParameters(parser.next());
                    String commandsInLoop = parser.next();
                    commandsInLoop = commandsInLoop.substring(1, commandsInLoop.length()-1).trim(); //chop of brackets
                    List<Instruction> listCommands = getInstructions(commandsInLoop); //commands in loop
                    InstructionListNode node = new InstructionListNode(cur);
                    for (Instruction instr: listCommands) {
                        node.addChild(instr); //add all instructions to the list
                    }
                    cur.addChild(node);
                } else { //Normal instruction
                    Instruction temp = InstructionFactory.getInstruction(parser.next(), cur);
                    cur.addChild(temp);
                    cur = temp;                    
                }
            }
            else {
                cur = cur.getParent();
            }
            if (cur == null) { //backtracked up to root's parent
                instructions.add(root);
                if (parser.hasNext()) {
                    root = InstructionFactory.getInstruction(parser.next(), null);
                    cur = root;
                } else {
                    return instructions;
                }
            }
        }
        instructions.add(root);
        return instructions;
    }
}
