package model;

import java.util.ArrayList;
import java.util.List;
import dataType.DataTypeChecker;
import model.instruction.*;
import model.instruction.error.TooFewParametersInstruction;


/**
 * 
 * Interpreter class that processes input from the user. It utilizes the parser class to properly
 * parse through the input and process on each part of the input. Creates new instances of
 * instructions using the instruction factory and puts these instructions in an instruction "tree"
 * to properly act upon combination instructions. Keeps track of the model that the interpreter
 * belongs to
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class Interpreter {
    private Model myModel;

    /**
     * Constructor for interpreter class. Stores the model that the interpreter belongs to
     * 
     * @param m The model that the interpreter belongs to
     */
    public Interpreter (Model m) {
        myModel = m;
    }

    /**
     * Parses the input that the user enters using an instance of the parser class. Creates new
     * instructions using the instruction factory and evaluates the instruction
     * 
     * @param input The string entered by the user
     * @return An error message if an error occurs
     */
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

    /**
     * Creates a list of instruction objects from the input from the user. If the user enters in
     * multiple commands in one input, adds all of the individual instructions into the list and
     * returns it
     * 
     * @param input A string with an instruction passed in by the interpreter
     * @return A list of instructions that is processed from the string passed in
     * @throws Exception The error message of the error if an error occurred
     */
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

    /**
     * Returns the number value of the passed in string, or the variable value if the variable
     * exists in the variable cache
     * 
     * @param paramString The string of the parameter to process
     * @return The value of the parameter
     */
    private double getParamValue (String paramString) {
        if (DataTypeChecker.isNumber(paramString)) { return Double.parseDouble(paramString); }
        return myModel.getVariableCache().get(paramString);
    }

    /**
     * Removes brackets from a given string
     * 
     * @param s The string to remove brackets from
     * @return The string without brackets
     * @throws Exception If the string doesn't start and end with left and right brackets, returns
     *         an error
     */
    private static String removeBrackets (String s) throws Exception {
        s = s.trim();
        if (!s.startsWith("[") || !s.endsWith("]")) { throw new Exception(
                                                                          "Expected a bracket-enclosed list."); }
        return s.substring(1, s.length() - 1);
    }
}
