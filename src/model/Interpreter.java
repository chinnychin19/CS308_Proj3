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
import model.instruction.multiturtle.InstructionASKWITH;
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
        List<Instruction> instructions = null;
        try {
            instructions = getInstructions(input);
        }
        catch (Exception e) {
            return e.getMessage();
        }
        for (Instruction instr : instructions) {
            try {
                instr.eval();
            }
            catch (Exception e) {
                return e.getMessage();
            }
        }
        return "";
    }

    public List<Instruction> getInstructions (String input) throws Exception {
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
                else if (cur instanceof InstructionASKWITH) {
                    String conditionList = parser.nextList();
                    conditionList = conditionList.substring(1, conditionList.length() - 1).trim();
                    List<Instruction> conditions = getInstructions(conditionList);
                    if (conditions.size() != 0) { throw new Exception(
                                                                      "Only one condition should be provided in ASKWITH"); }
                    cur.addChild(conditions.get(0)); // the only condition
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
                else { // Normal instruction or multi parameter command
                    String nextWord = parser.nextWord();
                    if (nextWord.startsWith("(")) { // it is a multi parameter command
                        // chop off parentheses
                        nextWord = nextWord.substring(1, nextWord.length() - 1).trim();
                        String[] tokens = nextWord.split("\\s");
                        String commandString = tokens[0];
                        String paramString = nextWord.substring(commandString.length()).trim();

                        InstructionMultiParameter multiParam =
                                new InstructionMultiParameter(cur, myModel);
                        Instruction command = myModel.getInstructionFactory()
                                .getInstruction(commandString, null);
                        List<Instruction> parameters = getInstructions(paramString);
                        multiParam.addChild(command); // command is first child
                        for (Instruction child : parameters) {
                            multiParam.addChild(child);
                        }
                        cur = cur.getParent();
                    }
                    else {
                        Instruction temp =
                                myModel.getInstructionFactory().getInstruction(nextWord, cur);
                        cur.addChild(temp);
                        cur = temp;
                    }
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

        // make sure all instructions have correct number of parameters in last branch
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

    private double getParamValue (String paramString) {
        if (DataTypeChecker.isNumber(paramString)) { return Double.parseDouble(paramString); }
        return myModel.getVariableCache().get(paramString);
    }
}
