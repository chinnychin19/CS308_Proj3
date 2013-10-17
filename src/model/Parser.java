package model;

import java.util.Stack;
import model.instruction.Instruction;
import model.instruction.InstructionFactory;
import model.instruction.loop.InstructionLoop;


public class Parser {
    private String myInput;
    private Model myModel;
    private final static char SPACE = ' ';

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

    public String nextExpression () {
        StringBuilder expression = new StringBuilder();
        String firstWord = nextWord();
        expression.append(firstWord + " ");
        Instruction root = myModel.getInstructionFactory().getInstruction(firstWord, null);
        Instruction cur = root;
        while (hasNext()) {
            if (cur.getChildren().size() < cur.getNumParams()) {
                if (cur instanceof InstructionLoop) { // TODO: and not a REPEAT loop
                    String parameters = nextList(); // enclosed in brackets
                    expression.append(parameters + " ");
                    ((InstructionLoop) cur).setParameters(parameters);
                    String commandsInLoop = nextList();
                    expression.append(commandsInLoop + " ");
                    cur.addChild(null); // the commands stored in the loop don't matter in this case
                }
                else { // Normal instruction
                    String nextInstruction = nextWord();
                    expression.append(nextInstruction + " ");
                    Instruction temp =
                            myModel.getInstructionFactory().getInstruction(nextInstruction, cur);
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
