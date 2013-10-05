package model;

import java.util.List;
import java.util.Map;
import model.command.Command;


public class Model {
    protected Interpreter myInterpreter;
    protected CommandCache myCommandCache;
    protected VariableCache myVariableCache;
    protected InstructionQueue myInstructionQueue;
    protected Turtle myTurtle;
    protected CommandHistory myCommandHistory;

    public Model () {
        myInterpreter = new Interpreter(this);
        myCommandCache = new CommandCache(this);
        myVariableCache = new VariableCache(this);
        myInstructionQueue = new InstructionQueue(this);
        myTurtle = new Turtle(this);
        myCommandHistory = new CommandHistory(this);
    }

    public void parseInput (String s) {
        myInterpreter.parseInput(s);
    }

    public String processNextInstruction () {
        return myInstructionQueue.processNextInstruction();
    }

    public boolean hasNextInstruction () {
        return myInstructionQueue.hasNextInstruction();
    }

    public int getTurtleX () {
        return myTurtle.getX();
    }

    public int getTurtleY () {
        return myTurtle.getY();
    }

    public int getTurtleAngle () {
        return myTurtle.getAngle();
    }

    public boolean isTurtleDrawing () {
        return myTurtle.isDrawing();
    }

    public boolean isTurtleVisible () {
        return myTurtle.isVisible();
    }

    public void putVariable (String key, String value) {
        myVariableCache.put(key, value);
    }

    public Map<String, String> getAllVariables () {
        return myVariableCache.getKeyValuePairs();
    }

    public void clearVariables () {
        myVariableCache.clear();
    }

    public Map<String, String> getAllCommands () {
        return myCommandCache.getAllCommands();
    }

    public void putCommand (String key, Command value) {
        myCommandCache.put(key, value);
    }

    public void clearCommands () {
        myCommandCache.clear();
    }

    public List<String> getHistory () {
        return myCommandHistory.getHistory();
    }

    public void clearHistory () {
        myCommandHistory.clear();
    }
}
