package model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import model.instruction.InstructionFactory;
import model.instruction.command.UserCommand;


public class Model {
    private Interpreter myInterpreter;
    private CommandCache myCommandCache;
    private VariableCache myVariableCache;
    private InstructionQueue myInstructionQueue;
    private Turtle myTurtle = new Turtle();
    private CommandHistory myCommandHistory;
    private InstructionFactory myInstructionFactory;

    public Model () {
        myInterpreter = new Interpreter(this);
        myCommandCache = new CommandCache();
        myVariableCache = new VariableCache();
        myInstructionQueue = new InstructionQueue();
        myTurtle = new Turtle();
        myCommandHistory = new CommandHistory();
        myInstructionFactory = new InstructionFactory(this);
    }

    public InstructionFactory getInstructionFactory () {
        return myInstructionFactory;
    }

    public Interpreter getInterpreter () {
        return myInterpreter;
    }

    public CommandCache getCommandCache () {
        return myCommandCache;
    }

    public VariableCache getVariableCache () {
        return myVariableCache;
    }

    public InstructionQueue getInstructionQueue () {
        return myInstructionQueue;
    }

    public Turtle getTurtle () {
        return myTurtle;
    }

    public CommandHistory getCommandHistory () {
        return myCommandHistory;
    }

    public String parseInput (String s) {
        String ret = myInterpreter.parseInput(s);
        // while (hasNextInstruction()) {
        // ret = processNextInstruction();
        // }
        return ret; // ret will be non-empty i.f.f. there is an error
    }

    protected String processNextInstruction () {
        return myInstructionQueue.processNextInstruction();
    }

    protected boolean hasNextInstruction () {
        return myInstructionQueue.hasNextInstruction();
    }

    public double getTurtleX () {
        return myTurtle.getX();
    }

    public double getTurtleY () {
        return myTurtle.getY();
    }

    public double getTurtleAngle () {
        return myTurtle.getAngle();
    }

    public boolean isTurtleVisible () {
        return myTurtle.isVisible();
    }

    public boolean isTurtleDrawing () {
        return myTurtle.isDrawing();
    }

    public Collection<Path> getTurtlePaths () {
        return myTurtle.getPaths();
    }

    public String putVariable (String key, String value) {
        return myVariableCache.put(key, value); // returns non-empty string if error
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

    public void putCommand (String key, UserCommand value) {
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
