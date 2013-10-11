package model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import model.instruction.command.UserCommand;


public class Model {
    protected static Interpreter myInterpreter;
    protected static CommandCache myCommandCache;
    protected static VariableCache myVariableCache;
    protected static InstructionQueue myInstructionQueue;
    protected static Turtle myTurtle = new Turtle();
    protected static CommandHistory myCommandHistory;

    public static void initModel () {
        myInterpreter = new Interpreter();
        myCommandCache = new CommandCache();
        myVariableCache = new VariableCache();
        myInstructionQueue = new InstructionQueue();
        myTurtle = new Turtle();
        myCommandHistory = new CommandHistory();
    }

    public static Interpreter getInterpreter () {
        return myInterpreter;
    }

    public static CommandCache getCommandCache () {
        return myCommandCache;
    }

    public static VariableCache getVariableCache () {
        return myVariableCache;
    }

    public static InstructionQueue getInstructionQueue () {
        return myInstructionQueue;
    }

    public static Turtle getTurtle () {
        return myTurtle;
    }

    public static CommandHistory getCommandHistory () {
        return myCommandHistory;
    }

    public static String parseInput (String s) {
        String ret = myInterpreter.parseInput(s);
        // while (hasNextInstruction()) {
        // ret = processNextInstruction();
        // }
        return ret; // ret will be non-empty i.f.f. there is an error
    }

    protected static String processNextInstruction () {
        return myInstructionQueue.processNextInstruction();
    }

    protected static boolean hasNextInstruction () {
        return myInstructionQueue.hasNextInstruction();
    }

    public static double getTurtleX () {
        return myTurtle.getX();
    }

    public static double getTurtleY () {
        return myTurtle.getY();
    }

    public static double getTurtleAngle () {
        return myTurtle.getAngle();
    }

    public static boolean isTurtleVisible () {
        return myTurtle.isVisible();
    }

    public static boolean isTurtleDrawing () {
        return myTurtle.isDrawing();
    }

    public static Collection<Path> getTurtlePaths () {
        return myTurtle.getPaths();
    }

    public static String putVariable (String key, String value) {
        return myVariableCache.put(key, value); // returns non-empty string if error
    }

    public static Map<String, String> getAllVariables () {
        return myVariableCache.getKeyValuePairs();
    }

    public static void clearVariables () {
        myVariableCache.clear();
    }

    public static Map<String, String> getAllCommands () {
        return myCommandCache.getAllCommands();
    }

    public static void putCommand (String key, UserCommand value) {
        myCommandCache.put(key, value);
    }

    public static void clearCommands () {
        myCommandCache.clear();
    }

    public static List<String> getHistory () {
        return myCommandHistory.getHistory();
    }

    public static void clearHistory () {
        myCommandHistory.clear();
    }
}
