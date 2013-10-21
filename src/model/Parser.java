package model;

import java.util.Stack;
import model.instruction.ComplexParameterInstruction;
import model.instruction.Instruction;
import model.instruction.InstructionString;
import model.instruction.InstructionTO;
import model.instruction.conditional.InstructionConditional;
import model.instruction.conditional.InstructionIF;
import model.instruction.loop.InstructionLoop;
import model.instruction.loop.InstructionREPEAT;


public class Parser {
    private String myInput;
    private Model myModel;
    private final static char SPACE = ' ';

    // TODO: methods should be throws exceptions

    protected Parser (String input, Model m) {
        input.replaceAll("\\s+", SPACE + "");
        myInput = input.trim();
        myModel = m;
    }

    public boolean hasNext () {
        myInput = myInput.trim();
        return !myInput.isEmpty();
    }

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
            // TODO: error, not at a list
            return null;
        }
    }

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
