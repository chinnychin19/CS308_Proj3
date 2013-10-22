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
        Parser parser = new Parser(input, myModel);
        String ret = "";
        while (parser.hasNext()) {
            try {
                List<Instruction> list = getInstructions(parser.nextExpression());
                for (Instruction instr : list) {
                    try {
                        instr.eval();
                    }
                    catch (Exception e) {
                        ret = ret + e.getMessage() + "\n";
                    }
                }
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
                if (cur instanceof ComplexParameterInstruction) {
                    ComplexParameterInstruction complexCur = (ComplexParameterInstruction) cur;
                    List<String> params = new ArrayList<String>();
                    for (int i = 0; i < complexCur.getNumWords(); i++) {
                        params.add(parser.nextWord());
                    }
                    for (int i = 0; i < complexCur.getNumExpressions(); i++) {
                        params.add(parser.nextExpression());
                    }
                    for (int i = 0; i < complexCur.getNumLists(); i++) {
                        params.add(removeBrackets(parser.nextList()));
                        // params must be stripped of outside brackets
                    }
                    complexCur.processParameters(params);
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

    private static String removeBrackets (String s) throws Exception {
        s = s.trim();
        if (!s.startsWith("[") || !s.endsWith("]")) { throw new Exception(
                                                                          "Expected a bracket-enclosed list."); }
        return s.substring(1, s.length() - 1);
    }
}
