package model;

import java.util.List;
import java.util.Map;
import model.command.Command;


public class Model {
    protected static Interpreter myInterpreter;
    protected static CommandCache myCommandCache;
    protected static VariableCache myVariableCache;
    protected static InstructionQueue myInstructionQueue;
    protected static Turtle myTurtle = new Turtle();
    protected static CommandHistory myCommandHistory;

    public static void initModel() {
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

    public static void parseInput (String s) {
        myInterpreter.parseInput(s);
    }

    public static String processNextInstruction () {
        return myInstructionQueue.processNextInstruction();
    }

    public static boolean hasNextInstruction () {
        return myInstructionQueue.hasNextInstruction();
    }

    public static int getTurtleX () {
        return myTurtle.getX();
    }

    public static int getTurtleY () {
        return myTurtle.getY();
    }

    public static int getTurtleAngle () {
        return myTurtle.getAngle();
    }

    public static boolean isTurtleDrawing () {
        return myTurtle.isDrawing();
    }

    public static boolean isTurtleVisible () {
        return myTurtle.isVisible();
    }

    public static void putVariable (String key, String value) {
        myVariableCache.put(key, value);
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

    public static void putCommand (String key, Command value) {
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

//    protected Interpreter myInterpreter;
//    protected CommandCache myCommandCache;
//    protected VariableCache myVariableCache;
//    protected InstructionQueue myInstructionQueue;
//    protected Turtle myTurtle;
//    protected CommandHistory myCommandHistory;
//
//    public Model () {
//        myInterpreter = new Interpreter(this);
//        myCommandCache = new CommandCache(this);
//        myVariableCache = new VariableCache(this);
//        myInstructionQueue = new InstructionQueue(this);
//        myTurtle = new Turtle(this);
//        myCommandHistory = new CommandHistory(this);
//    }
//
//    protected Interpreter getInterpreter () {
//        return myInterpreter;
//    }
//
//    protected CommandCache getCommandCache () {
//        return myCommandCache;
//    }
//
//    protected VariableCache getVariableCache () {
//        return myVariableCache;
//    }
//
//    protected InstructionQueue getInstructionQueue () {
//        return myInstructionQueue;
//    }
//
//    protected Turtle getTurtle () {
//        return myTurtle;
//    }
//
//    protected CommandHistory getCommandHistory () {
//        return myCommandHistory;
//    }
//
//    public void parseInput (String s) {
//        myInterpreter.parseInput(s);
//    }
//
//    public String processNextInstruction () {
//        return myInstructionQueue.processNextInstruction();
//    }
//
//    public boolean hasNextInstruction () {
//        return myInstructionQueue.hasNextInstruction();
//    }
//
//    public int getTurtleX () {
//        return myTurtle.getX();
//    }
//
//    public int getTurtleY () {
//        return myTurtle.getY();
//    }
//
//    public int getTurtleAngle () {
//        return myTurtle.getAngle();
//    }
//
//    public boolean isTurtleDrawing () {
//        return myTurtle.isDrawing();
//    }
//
//    public boolean isTurtleVisible () {
//        return myTurtle.isVisible();
//    }
//
//    public void putVariable (String key, String value) {
//        myVariableCache.put(key, value);
//    }
//
//    public Map<String, String> getAllVariables () {
//        return myVariableCache.getKeyValuePairs();
//    }
//
//    public void clearVariables () {
//        myVariableCache.clear();
//    }
//
//    public Map<String, String> getAllCommands () {
//        return myCommandCache.getAllCommands();
//    }
//
//    public void putCommand (String key, Command value) {
//        myCommandCache.put(key, value);
//    }
//
//    public void clearCommands () {
//        myCommandCache.clear();
//    }
//
//    public List<String> getHistory () {
//        return myCommandHistory.getHistory();
//    }
//
//    public void clearHistory () {
//        myCommandHistory.clear();
//    }
}
