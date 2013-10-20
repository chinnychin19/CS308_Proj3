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
import model.instruction.multiturtle.InstructionASK;
import model.instruction.multiturtle.InstructionTELL;
import model.instruction.error.ErrorInstruction;
import model.instruction.error.InvalidCommandInstruction;
import model.instruction.error.TooFewParametersInstruction;


public class Interpreter {
    // TODO: when complete, refactor out repeated code for interpreting lists
    private Model myModel;

    public Interpreter (Model m) {
        myModel = m;
    }

    protected String parseInput (String input) {
        input = input.replaceAll("\\s+", " "); // all white space becames a ' ' (space character)
        input = input.trim();
        if (input.isEmpty()) { return ""; }
        List<Instruction> instructions = getInstructions(input);
        for (Instruction instr : instructions) {
            // Model.getInstructionQueue().add(instr);
            // Model.processNextInstruction();
            try {
                instr.eval();
            }
            catch (Exception e) {
                return e.getMessage();
            }
        }
        return "";
    }

    public List<Instruction> getInstructions (String input) { // TODO: should be throws exception
        Parser parser = new Parser(input, myModel);
        List<Instruction> instructions = new ArrayList<Instruction>();
        if (!parser.hasNext()) { return instructions; }
        Instruction root = myModel.getInstructionFactory().getInstruction(parser.nextWord(), null);
        Instruction cur = root;
        if (root == null) {
            instructions.add(new InvalidCommandInstruction(myModel));
            return instructions;
        }
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
                    InstructionListNode node = new InstructionListNode(cur, myModel);
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
                        InstructionListNode node = new InstructionListNode(cur, myModel);
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
                    cur.addChild(new InstructionString(name, cur, myModel));
                    cur.addChild(new InstructionString(params, cur, myModel));
                    cur.addChild(new InstructionString(commands, cur, myModel));
                    try {
                        cur.eval();
                    }
                    catch (Exception e) {
                        // need to return the current list, plus an error node
                        instructions.add(new ErrorInstruction("Error in the TO command", myModel));
                        return instructions;
                    }
                }
                else if (cur instanceof UserCommand) {
                    // int paramsEntered = cur.getChildren().size();
                    // int paramIndex = cur.getNumParams() - paramsEntered - 2;
                    // if (paramIndex < 0) {
                    // cur = cur.getParent();
                    // }
                    // String paramName = ((UserCommand) cur).getParamNames().get(paramIndex);
                    // cur.addChild(new InstructionVariable(paramName, cur));
                    // String param = parser.nextExpression();
                    // double value;
                    // try {
                    // value =
                    // ((InstructionConstant)getInstructions(param).get(0).eval()).getValue();
                    // }
                    // catch (Exception e) {
                    // instructions.add(new ErrorInstruction("Something went wrong"));
                    // return instructions;
                    // }
                    // Model.getVariableCache().put(paramName,
                    // value);

                    List<String> paramNames = ((UserCommand) cur).getParamNames();
                    for (int i = 0; i < paramNames.size(); i++) {
                        cur.addChild(new InstructionVariable(paramNames.get(i), cur, myModel));
                        String param = parser.nextWord();
                        myModel.getVariableCache().put(paramNames.get(i),
                                                       getParamValue(param));
                    }
                }
                else if (cur instanceof InstructionTELL) {
                    String idList = parser.nextList();
                    idList = idList.substring(1, idList.length() - 1).trim();
                    // TODO: bracket chopping should become a function
                    List<Instruction> parameters = getInstructions(idList);
                    for (Instruction child : parameters) {
                        cur.addChild(child);
                    }
                    cur = cur.getParent();
                }
                else if (cur instanceof InstructionASK) {
                    String idList = parser.nextList();
                    idList = idList.substring(1, idList.length() - 1).trim();
                    List<Instruction> parameters = getInstructions(idList);
                    for (Instruction child : parameters) {
                        cur.addChild(child);
                    }
                    String commandList = parser.nextList();
                    commandList = commandList.substring(1, commandList.length() - 1).trim();
                    List<Instruction> commands = getInstructions(commandList);
                    InstructionListNode commandsNode = new InstructionListNode(cur, myModel);
                    for (Instruction child : commands) {
                        commandsNode.addChild(child);
                    }
                    cur.addChild(commandsNode);
                    cur = cur.getParent();
                }
                else { // Normal instruction
                    Instruction temp =
                            myModel.getInstructionFactory().getInstruction(parser.nextWord(), cur);
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
                    root = myModel.getInstructionFactory().getInstruction(parser.nextWord(), null);
                    cur = root;
                    if (root == null) {
                        instructions.add(new InvalidCommandInstruction(myModel));
                        return instructions;
                    }
                }
                else {
                    return instructions;
                }
            }
        }

        // make sure all instruction have correct number of parameters
        while (cur != null) {
            if (cur.getChildren().size() != cur.getNumParams()) {
                instructions.add(new TooFewParametersInstruction(myModel));
                return instructions;
            }
            cur = cur.getParent();
        }

        instructions.add(root);
        return instructions;
    }

    private double getParamValue (String nextWord) {
        if (DataTypeChecker.isNumber(nextWord)) { return Double.parseDouble(nextWord); }
        return myModel.getVariableCache().get(nextWord);
    }
}
