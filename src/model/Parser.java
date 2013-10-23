package model;

import java.util.Stack;
import model.instruction.ComplexParameterInstruction;
import model.instruction.Instruction;


/**
 * 
 * Parser class that parses in text entered by the user. Breaks down commands into parts to pass
 * back to the interpreter. Stores the input entered by the user, the model that the parser belongs
 * to, and a character constant that stores a space
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class Parser {
    private String myInput;
    private Model myModel;
    private final static char SPACE = ' ';

    /**
     * Constructor for the parser that creates an instance of a parser. Stores the input as a
     * trimmed input by replacing all new lines with spaces. Stores the model that the parser
     * belongs to
     * 
     * @param input The string that the user has entered
     * @param m The model that the parser belongs to
     */
    protected Parser (String input, Model m) {
        input.replaceAll("\\s+", SPACE + "");
        myInput = input.trim();
        myModel = m;
    }

    /**
     * @return True if the parsed input has more to be parsed, false if there is nothing left to be
     *         parsed
     */
    public boolean hasNext () {
        myInput = myInput.trim();
        return !myInput.isEmpty();
    }

    /**
     * @return The next word that is parsed. A word is defined as anything not enclosed in brackets.
     *         Does include multi parameter expressions, however
     */
    public String nextWord () {
        // special case: multi parameter expression
        if (myInput.charAt(0) == '(') {
            // Return the string encased in the outer parentheses
            Stack<Character> stack = new Stack<Character>();
            stack.push('[');
            int stoppingPoint = -1;
            for (int i = 1; i < myInput.length(); i++) {
                if (myInput.charAt(i) == '(') {
                    stack.push('(');
                }
                else if (myInput.charAt(i) == ')') {
                    stack.pop();
                    if (stack.isEmpty()) {
                        stoppingPoint = i;
                        break;
                    }
                }
            }
            String ret = myInput.substring(0, stoppingPoint + 1); // stores (...)
            myInput = myInput.substring(stoppingPoint + 1).trim();
            return ret.trim();
        }
        if (myInput.contains(SPACE + "")) {
            String ret = myInput.substring(0, myInput.indexOf(SPACE));
            myInput = myInput.substring(myInput.indexOf(SPACE)).trim();
            return ret;
        }
        String ret = myInput.trim();
        myInput = "";
        return ret;
    }

    /**
     * @return The next list that is parsed. A list is defined as something in brackets
     */
    public String nextList () {
        myInput = myInput.trim();
        if (myInput.charAt(0) == '[') {
            // Return the string encased in the outer brackets
            Stack<Character> stack = new Stack<Character>();
            stack.push('[');
            int stoppingPoint = -1;
            for (int i = 1; i < myInput.length(); i++) {
                if (myInput.charAt(i) == '[') {
                    stack.push('[');
                }
                else if (myInput.charAt(i) == ']') {
                    stack.pop();
                    if (stack.isEmpty()) {
                        stoppingPoint = i;
                        break;
                    }
                }
            }
            String ret = myInput.substring(0, stoppingPoint + 1); // stores [...]
            myInput = myInput.substring(stoppingPoint + 1).trim();
            return ret.trim();
        }
        else {
            return null;
        }
    }

    /**
     * @return The next expression that is parsed. An expression is defined as parameters for an
     *         instruction
     * @throws Exception An error if an error occurs, such as not enough parameters for the given
     *         instruction
     */
    public String nextExpression () throws Exception {
        StringBuilder expression = new StringBuilder();
        String firstWord = nextWord();
        expression.append(firstWord + " ");
        Instruction root = myModel.getInstructionFactory().getInstruction(firstWord, null);
        Instruction cur = root;
        while (hasNext()) {
            if (cur.getChildren().size() < cur.getNumParams()) {
                if (cur instanceof ComplexParameterInstruction) {
                    ComplexParameterInstruction complexCur = (ComplexParameterInstruction) cur;
                    for (int i = 0; i < complexCur.getNumWords(); i++) {
                        expression.append(nextWord() + " ");
                    }
                    for (int i = 0; i < complexCur.getNumExpressions(); i++) {
                        expression.append(nextExpression() + " ");
                    }
                    for (int i = 0; i < complexCur.getNumLists(); i++) {
                        expression.append(nextList() + " ");
                    }
                    cur = cur.getParent(); // all children account for
                }
                else { // Normal instruction
                    String nextInstruction = nextWord();
                    expression.append(nextInstruction + " ");
                    Instruction temp = null;
                    temp = myModel.getInstructionFactory().getInstruction(nextInstruction, cur);
                    cur.addChild(temp);
                    cur = temp;
                }
            }
            else {
                cur = cur.getParent();
            }
            if (cur == null) { // backtracked up to root's parent
                return expression.toString().trim();
            }
        }
        return expression.toString().trim();
    }

}
