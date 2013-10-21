package model;

import java.util.ArrayList;
import java.util.List;
import dataType.DataTypeChecker;
import model.instruction.*;
import model.instruction.command.UserCommand;
import model.instruction.conditional.InstructionConditional;
import model.instruction.conditional.InstructionIF;
import model.instruction.loop.InstructionLoop;
import model.instruction.loop.InstructionREPEAT;
import model.instruction.multiturtle.InstructionASK;
import model.instruction.multiturtle.InstructionASKWITH;
import model.instruction.multiturtle.InstructionTELL;
import model.instruction.error.ErrorInstruction;
import model.instruction.error.TooFewParametersInstruction;


public class Interpreter {
    private Model myModel;

    public Interpreter (Model m) {
        myModel = m;
    }

    protected String parseInput (String input) {
        input = input.replaceAll("\\s+", " "); // all white space becames a ' ' (space character)
        input = input.trim();
        if (input.isEmpty()) { return ""; }
        List<Instruction> instructions = new ArrayList<Instruction>();
        Parser parser = new Parser(input, myModel);
        String ret = "";
        while (parser.hasNext()) {
            try {
                List<Instruction> list = getInstructions(parser.nextExpression());
                instructions.addAll(list);
            }
            catch (Exception e) {
                ret = ret + e.getMessage() + "\n";
            }
        }
        for (Instruction instr : instructions) {
            try {
                instr.eval();
            }
            catch (Exception e) {
                ret = ret + e.getMessage() + "\n";
            }
        }
        return ret;
    }

    public List<Instruction> getInstructions (String input) throws Exception {
        Parser parser = new Parser(input, myModel);
        List<Instruction> instructions = new ArrayList<Instruction>();
        if (!parser.hasNext()) { return instructions; }
        Instruction root = myModel.getInstructionFactory().getInstruction(parser.nextWord(), null);
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
                    commandsInLoop = removeBrackets(commandsInLoop);
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
                    int numLists = (cur instanceof InstructionIF) ? 1 : 2;
                    for (int listIndex = 0; listIndex < numLists; listIndex++) {
                        String commandsInLoop = parser.nextList();
                        commandsInLoop = removeBrackets(commandsInLoop);
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
                    idList = removeBrackets(idList);
                    List<Instruction> parameters = getInstructions(idList);
                    for (Instruction child : parameters) {
                        cur.addChild(child);
                    }
                    cur = cur.getParent();
                }
                else if (cur instanceof InstructionASK) {
                    String idList = parser.nextList();
                    idList = removeBrackets(idList);
                    List<Instruction> parameters = getInstructions(idList);
                    for (Instruction child : parameters) {
                        cur.addChild(child);
                    }
                    String commandList = parser.nextList();
                    commandList = removeBrackets(commandList);
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
                    conditionList = removeBrackets(conditionList);
                    List<Instruction> conditions = getInstructions(conditionList);
                    if (conditions.size() != 1) { throw new Exception(
                                                                      "Only one condition should be provided in ASKWITH"); }
                    cur.addChild(conditions.get(0)); // the only condition
                    String commandList = parser.nextList();
                    commandList = removeBrackets(commandList);
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
                    Instruction temp =
                            myModel.getInstructionFactory().getInstruction(nextWord, cur);
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
                }
                else {
                    return instructions;
                }
            }
        }

        // make sure all instructions have correct number of parameters in last branch
        while (cur != null) {
            if (!(cur instanceof InstructionMultiParameter) &&
                cur.getChildren().size() != cur.getNumParams()) {
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

    private static String removeBrackets (String s) {
        return s.substring(1, s.length() - 1).trim();
    }
}
