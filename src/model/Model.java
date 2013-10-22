package model;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.instruction.InstructionFactory;
import model.instruction.command.UserCommand;
import model.instruction.error.FileNotFound;


/**
 * 
 * Model class that keeps track of all aspects of a SLogo environment. This is the API that
 * interfaces with the view. It keeps track of the interpreter, the command cache, the variable
 * cache, the turtles, the command history, the instruction factory that creates new instruction
 * objects, the current language, the current background color, the current pen color, the current
 * pen size, the current available colors, the current available shapes, the current available
 * languages, and the current shape index. It contains public methods that the view uses to act upon
 * the various aspects of the SLogo environment such as turtles, commands, and variables while
 * protecting these aspects from the view.
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */

public class Model {
    private Interpreter myInterpreter;
    private CommandCache myCommandCache;
    private VariableCache myVariableCache;
    private Map<Integer, Turtle> myTurtles;
    private CommandHistory myCommandHistory;
    private InstructionFactory myInstructionFactory;
    private String myLanguage;
    private int myBGColorIndex;
    private int myPenColorIndex;
    private int myPenSize;
    private List<Color> myAvailableColors;
    private List<String> myAvailableShapes;
    private List<String> myAvailableLanguages;
    private int myShapeIndex;

    /**
     * 
     */
    public Model () {
        initializeColors();
        initializeShapes();
        initializeLanguages();
        myInterpreter = new Interpreter(this);
        myCommandCache = new CommandCache();
        myVariableCache = new VariableCache();
        myTurtles = new HashMap<Integer, Turtle>();
        myTurtles.put(1, new Turtle(1, this)); // 1 turtle with ID=1 by default
        myCommandHistory = new CommandHistory();
        myInstructionFactory = new InstructionFactory(this);
        myLanguage = "English";
        myPenSize = 1;
        myShapeIndex = 0;
    }

    private void initializeLanguages () {
        myAvailableLanguages = new ArrayList<String>();
        myAvailableLanguages.add("English");
        myAvailableLanguages.add("French");
    }

    private void initializeShapes () {
        myAvailableShapes = new ArrayList<String>();
        myAvailableShapes.add("Turtle1");
        myAvailableShapes.add("Turtle2");
        myAvailableShapes.add("Turtle3");
    }

    private void initializeColors () {
        myBGColorIndex = 0;
        myPenColorIndex = 1;
        myAvailableColors = new ArrayList<Color>();
        myAvailableColors.add(myBGColorIndex, Color.black);
        myAvailableColors.add(myPenColorIndex, Color.red);

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

    public Turtle getTurtle (int id) {
        if (myTurtles.keySet().contains(id)) { return myTurtles.get(id); }
        Turtle ret = new Turtle(id, this);
        myTurtles.put(id, ret);
        return ret;
    }

    public CommandHistory getCommandHistory () {
        return myCommandHistory;
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
        for (int id : getActiveTurtleIDs()) {
            list.add(myTurtles.get(id));
        }
        return list;
    }

    public String setPalette (int index, int r, int g, int b) {
        if (index < 0 || index > myAvailableColors.size()) { return "Index is out of range"; }
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) { return "RGB values must be in range 0-255"; }
        if (index < myAvailableColors.size()) { // edit pre-existing
            myAvailableColors.remove(index);
            myAvailableColors.add(index, new Color(r, g, b));
        }
        else {
            myAvailableColors.add(new Color(r, g, b));
        }
        return "";
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

    public String getTurtleShape (int id) {
        return myAvailableShapes.get(getTurtle(id).getShapeIndex());
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

    public Collection<Stamp> getTurtleStamps () {
        Collection<Stamp> list = new ArrayList<Stamp>();
        for (int id : myTurtles.keySet()) {
            list.addAll(myTurtles.get(id).getStamps());
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
        String oldLanguage = myLanguage;
        try {
            Scanner sc = new Scanner(new File(filename));
            setLanguage("English"); // file must be read in English
            String toBeParsed = "";
            while (sc.hasNext()) {
                toBeParsed = toBeParsed + sc.next() + " ";
            }
            sc.close();
            parseInput(toBeParsed);
            setLanguage(oldLanguage); // reset language to what it was before
        }
        catch (Exception e) {
            return FileNotFound.MESSAGE;
        }
        return null;
    }

    public String saveLibrary (String filename) {
        if (myLanguage != "English") { return "Only English library files may be saved."; }
        try {
            PrintStream out = new PrintStream(new File(filename));
            Map<String, String> variables = myVariableCache.getKeyValuePairs();
            for (String name : variables.keySet()) {
                String line = "MAKE " + name + " " + variables.get(name);
                out.println(line);
            }
            Map<String, String> commands = myCommandCache.getAllCommands();
            for (String name : commands.keySet()) {
                String line = commands.get(name);
                out.println(line);
            }
            out.close();
            return "";
        }
        catch (FileNotFoundException e) {
            return e.getMessage();
        }
    }

    public List<String> getAvailableLanguages () {
        return myAvailableLanguages;
    }

    public String setLanguage (String language) {
        String ret = myInstructionFactory.setLanguage(language);
        if (ret.isEmpty()) {
            myLanguage = language;
        }
        return ret;
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

    public Collection<Integer> getActiveTurtleIDs () {
        Collection<Integer> list = new ArrayList<Integer>();
        for (int id : myTurtles.keySet()) {
            if (myTurtles.get(id).isActive()) {
                list.add(id);
            }
        }
        return list;
    }

    public Collection<Integer> getAllTurtleIDs () {
        Collection<Integer> list = new ArrayList<Integer>();
        for (int id : myTurtles.keySet()) {
            list.add(id);
        }
        return list;
    }

    public String setBGColor (int colorIndex) {
        myCommandHistory.add("SETBG " + colorIndex);
        if (colorIndex < 0 || colorIndex >= myAvailableColors.size()) { return "Index is out of range"; }
        myBGColorIndex = colorIndex;
        return "";
    }

    public Color getBGColor () {
        return myAvailableColors.get(myBGColorIndex);
    }

    public String setPenColor (int colorIndex) {
        myCommandHistory.add("SETPC " + colorIndex);
        if (colorIndex < 0 || colorIndex >= myAvailableColors.size()) { return "Index is out of range"; }
        myPenColorIndex = colorIndex;
        for (Turtle t : getActiveTurtles()) {
            t.setColor(myAvailableColors.get(colorIndex));
        }
        return "";
    }

    public Color getPenColor () {
        return myAvailableColors.get(myPenColorIndex);
    }

    public String setPenSize (int pixels) {
        myCommandHistory.add("SETPS " + pixels);
        if (pixels < 0) { return "Pen size must be non-negative"; }
        myPenSize = pixels;
        for (Turtle t : getActiveTurtles()) {
            t.setPenSize(pixels);
        }
        return "";
    }

    public int getPenSize () {
        return myPenSize;
    }

    public List<Color> getAvailableColors () {
        return myAvailableColors;
    }

    public String getShape () {
        return myAvailableShapes.get(myShapeIndex);
    }

    public String setShape (int shapeIndex) {
        myCommandHistory.add("SETSH " + shapeIndex);
        if (shapeIndex < 0 || shapeIndex >= myAvailableShapes.size()) { return "Index is out of range"; }
        myShapeIndex = shapeIndex;
        for (Turtle t : getActiveTurtles()) {
            t.setShape(shapeIndex);
        }
        return "";
    }

    public int getBGColorIndex () {
        return myBGColorIndex;
    }

    public int getPenColorIndex () {
        return myPenColorIndex;
    }

    public int getShapeIndex () {
        return myShapeIndex;
    }

    public List<String> getAvailableShapes () {
        return myAvailableShapes;
    }

    public String keyPressed (int keyCode) {
        if (myCommandCache.contains("ONKEY")) { return parseInput("ONKEY " + keyCode); }
        return "";
    }

    public String mouseClicked (int x, int y) {
        if (myCommandCache.contains("ONCLICK")) { return parseInput("ONCLICK " + x + " " + y); }
        return "";
    }

    public String mouseMoved (int x, int y) {
        if (myCommandCache.contains("ONMOVE")) { return parseInput("ONMOVE " + x + " " + y); }
        return "";
    }
}
