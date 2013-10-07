package model;

import java.util.Stack;


public class Parser {
    private String myInput;
    private final static char SPACE = ' ';

    protected Parser (String input) {
        input.replaceAll("\\s+", SPACE + "");
        myInput = input.trim();
    }

    public boolean hasNext () {
        return !myInput.isEmpty();
    }

    public String next () {
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
            return ret;
        }
        else if (myInput.substring(1).contains(SPACE + "")) {
            String ret = myInput.substring(0, myInput.indexOf(SPACE));
            myInput = myInput.substring(myInput.indexOf(SPACE)).trim();
            return ret;
        }
        return myInput.trim();
    }
}
