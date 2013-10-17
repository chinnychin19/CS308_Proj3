package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.instruction.InstructionFactory;
import model.instruction.command.UserCommand;


public class Model {
    private Interpreter myInterpreter;
    private CommandCache myCommandCache;
    private VariableCache myVariableCache;
    private InstructionQueue myInstructionQueue;
    private Map<Integer, Turtle> myTurtles;
    private List<Integer> myActiveTurtleIDs;
    private CommandHistory myCommandHistory;
    private InstructionFactory myInstructionFactory;

    public Model () {
        myInterpreter = new Interpreter(this);
        myCommandCache = new CommandCache();
        myVariableCache = new VariableCache();
        myInstructionQueue = new InstructionQueue();
        myTurtles = new HashMap<Integer, Turtle>();
        myActiveTurtleIDs = new ArrayList<Integer>();
        myTurtles.put(1, new Turtle(1)); // 1 turtle with ID=1 by default
        myActiveTurtleIDs.add(1); // by default, initial turtle is active
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

    public Turtle getTurtle (int id) {
        if (myTurtles.keySet().contains(id)) { return myTurtles.get(id); }
        Turtle ret = new Turtle(id);
        myTurtles.put(id, ret);
        myActiveTurtleIDs.add(id);
        return ret;
    }

    public CommandHistory getCommandHistory () {
        return myCommandHistory;
    }

    protected String processNextInstruction () {
        return myInstructionQueue.processNextInstruction();
    }

    protected boolean hasNextInstruction () {
        return myInstructionQueue.hasNextInstruction();
    }

    public void clearVariables () {
        myVariableCache.clear();
    }

    public void putCommand (String key, UserCommand value) {
        myCommandCache.put(key, value);
    }

    public void clearCommands () {
        myCommandCache.clear();
    }

    public void clearHistory () {
        myCommandHistory.clear();
    }

    public void clearPaths () {
        for (int id : myTurtles.keySet()) {
            myTurtles.get(id).clearPaths();
        }
    }

    public void clearStamps () {
        for (int id : myTurtles.keySet()) {
            myTurtles.get(id).clearStamps();
        }
    }

    public Collection<Turtle> getActiveTurtles () {
        Collection<Turtle> list = new ArrayList<Turtle>();
        for (int id : myActiveTurtleIDs) {
            list.add(myTurtles.get(id));
        }
        return list;
    }

    // View functions:
    public String parseInput (String s) {
        myCommandHistory.add(s);
        String ret = myInterpreter.parseInput(s);
        return ret; // ret will be non-empty i.f.f. there is an error
    }

    public double getTurtleX (int id) {
        return getTurtle(id).getX();
    }

    public double getTurtleY (int id) {
        return getTurtle(id).getY();
    }

    public double getTurtleAngle (int id) {
        return getTurtle(id).getAngle();
    }

    public boolean isTurtleVisible (int id) {
        return getTurtle(id).isVisible();
    }

    public boolean isTurtleDrawing (int id) {
        return getTurtle(id).isDrawing();
    }

    public Collection<Path> getTurtlePaths () {
        Collection<Path> list = new ArrayList<Path>();
        for (int id : myTurtles.keySet()) {
            list.addAll(myTurtles.get(id).getPaths());
        }
        return list;
    }

    public String putVariable (String key, String value) {
        return myVariableCache.put(key, value); // returns non-empty string if error
    }

    public Map<String, String> getAllVariables () {
        return myVariableCache.getKeyValuePairs();
    }

    public Map<String, String> getAllCommands () {
        return myCommandCache.getAllCommands();
    }

    public List<String> getHistory () {
        return myCommandHistory.getHistory();
    }

    public String readLibrary (String filename) {
        // TODO
        return null;
    }

    public String saveLibrary (String filename) {
        // TODO
        return null;
    }

    public String setLanguage (String language) {
        // TODO
        return null;
    }

    public boolean canUndo () {
        return false; // TODO
    }

    public String undo () {
        return null; // TODO
    }

    public boolean canRedo () {
        return false; // TODO
    }

    public String redo () {
        return null; // TODO
    }

    public List<Integer> getActiveTurtleIDs () {
        return null; // TODO
    }

    public List<Integer> getAllTurtleIDs () {
        return null; // TODO
    }

    public String setBGColor (int colorIndex) {
        return null; // TODO
    }

    public int[] getBGColor () {
        return null; // TODO
    }

    public String setPenColor (int colorIndex) {
        return null; // TODO
    }

    public int[] getPenColor () {
        return null; // TODO
    }

    public String setPenSize (int pixels) {
        return null; // TODO
    }

    public int getPenSize () {
        return 0; // TODO
    }

    public String keyPressed (int k) {
        return null; // TODO
    }

    public String mouseClicked (int x, int y) {
        return null; // TODO
    }

    public String mouseMoved (int x, int y) {
        return null; // TODO
    }
}
