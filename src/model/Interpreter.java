package model;

import java.util.ArrayList;
import java.util.List;
import dataType.DataTypeChecker;
import model.instruction.*;
import model.instruction.command.UserCommand;
import model.instruction.conditional.InstructionConditional;
import model.instruction.conditional.InstructionIF;
import model.instruction.conditional.InstructionIFELSE;
import model.instruction.loop.InstructionLoop;
import model.instruction.loop.InstructionREPEAT;
import model.instruction.error.Error;


public class Interpreter {
    // TODO: when complete, refactor out repeated code for interpreting lists

    protected String parseInput (String input) {
        input = input.replaceAll("\\s+", " "); // all white space becames a ' ' (space character)
        input = input.trim();
        if (input.isEmpty()) { return ""; }
        Model.getCommandHistory().add(input);
        List<Instruction> instructions = getInstructions(input);
        for (Instruction instr : instructions) {
            // Model.getInstructionQueue().add(instr);
            // Model.processNextInstruction();
            Instruction result = instr.eval();
            if (result instanceof Error) {
                return ((Error)result).getMessage();
            }
        }
        return "";
    }

    public List<Instruction> getInstructions (String input) {
        Parser parser = new Parser(input);
        List<Instruction> instructions = new ArrayList<Instruction>();
        if (!parser.hasNext()) { return instructions; }
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
                else if (cur instanceof InstructionConditional) {
                    String strCondition = parser.nextExpression();
                    cur.addChild(getInstructions(strCondition).get(0));
                    int numLists =
                            (cur instanceof InstructionIF) ? 1 :
                                                          (cur instanceof InstructionIFELSE) ? 2
                                                                                            : -1;
                    // -1 should not happen
                    for (int listIndex = 0; listIndex < numLists; listIndex++) {
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
                }
                else if (cur instanceof InstructionTO) {
                    String name = parser.nextWord();
                    String params = parser.nextList();
                    String commands = parser.nextList();
                    cur.addChild(new InstructionString(name, cur));
                    cur.addChild(new InstructionString(params, cur));
                    cur.addChild(new InstructionString(commands, cur));
                    cur.eval();
                }
                else if (cur instanceof UserCommand) {
                    List<String> paramNames = ((UserCommand) cur).getParamNames();
                    for (int i = 0; i < paramNames.size(); i++) {
                        cur.addChild(new InstructionVariable(paramNames.get(i), cur));
                        String param = parser.nextWord();
                        Model.getVariableCache().put(paramNames.get(i),
                                                     getParamValue(param));
                    }
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

    private double getParamValue (String nextWord) {
        if (DataTypeChecker.isNumber(nextWord)) { return Double.parseDouble(nextWord); }
        return Model.getVariableCache().get(nextWord);
    }
}
