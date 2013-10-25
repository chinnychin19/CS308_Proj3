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
import stack.TurtleStack;
import model.instruction.InstructionFactory;
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
    private TurtleStack myUndoStack;
    private TurtleStack myRedoStack;

    /**
     * Constructor for an instance of a model. It initializes the available colors to black and red
     * and sets the background color to black and the pen color to red. It initializes the available
     * shapes to the 3 default turtle shapes and sets the current shape to the first default turtle
     * shape. It initializes the available languages to English and French and sets the current
     * language to English. It initializes a list of turtles and creates a new turtle with an ID of
     * 1. It sets the current pen size to 1. It initializes an interpreter, command and variable
     * cache, command history, and instruction factory for this model instance.
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
        myUndoStack = new TurtleStack();
        myRedoStack = new TurtleStack();
    }

    /**
     * Initializes the available languages of the current model (currently English and French)
     */
    private void initializeLanguages () {
        myAvailableLanguages = new ArrayList<String>();
        myAvailableLanguages.add("English");
        myAvailableLanguages.add("French");
    }

    /**
     * Initializes the available shapes of the current model (3 default turtles)
     */
    private void initializeShapes () {
        myAvailableShapes = new ArrayList<String>();
        myAvailableShapes.add("Turtle1");
        myAvailableShapes.add("Turtle2");
        myAvailableShapes.add("Turtle3");
    }

    /**
     * Initializes the available colors of the current model (black and red) and sets the background
     * color to black and the pen color to red
     */
    private void initializeColors () {
        myBGColorIndex = 0;
        myPenColorIndex = 1;
        myAvailableColors = new ArrayList<Color>();
        myAvailableColors.add(myBGColorIndex, Color.black);
        myAvailableColors.add(myPenColorIndex, Color.red);
        myAvailableColors.add(Color.yellow);
        myAvailableColors.add(Color.green);
        myAvailableColors.add(Color.blue);

    }

    /**
     * @return The instruction factory of the current model. Should not be used by view
     */
    public InstructionFactory getInstructionFactory () {
        return myInstructionFactory;
    }

    /**
     * @return The interpreter of the current model. Should not be used by view
     */
    public Interpreter getInterpreter () {
        return myInterpreter;
    }

    /**
     * @return The command cache of the current model. Should not be used by view
     */
    public CommandCache getCommandCache () {
        return myCommandCache;
    }

    /**
     * @return The variable cache of the current model. Should not be used by view
     */
    public VariableCache getVariableCache () {
        return myVariableCache;
    }

    /**
     * Gets a turtle from the model with a given ID
     * 
     * @param id The ID of the desired turtle
     * @return The turtle object pertaining to the given ID
     */
    public Turtle getTurtle (int id) {
        if (myTurtles.keySet().contains(id)) { return myTurtles.get(id); }
        Turtle ret = new Turtle(id, this);
        myTurtles.put(id, ret);
        return ret;
    }

    /**
     * @return The command history of the current model. Should not be used by view
     */
    public CommandHistory getCommandHistory () {
        return myCommandHistory;
    }

    /**
     * Clears the variables in the variable cache
     */
    public void clearVariables () {
        myVariableCache.clear();
    }

    /**
     * Clears the commands in the command cache
     */
    public void clearCommands () {
        myCommandCache.clear();
    }

    /**
     * Clears the commands in the command history
     */
    public void clearHistory () {
        myCommandHistory.clear();
    }

    /**
     * Clears the paths currently in the SLogo environment. Should not be used by view
     */
    public void clearPaths () {
        for (int id : myTurtles.keySet()) {
            myTurtles.get(id).clearPaths();
        }
    }

    /**
     * Clears the stamps currently in the SLogo environment
     */
    public void clearStamps () {
        for (int id : myTurtles.keySet()) {
            myTurtles.get(id).clearStamps();
        }
    }

    /**
     * @return The active turtles in the current model
     */
    public Collection<Turtle> getActiveTurtles () {
        Collection<Turtle> list = new ArrayList<Turtle>();
        for (int id : getActiveTurtleIDs()) {
            list.add(myTurtles.get(id));
        }
        return list;
    }

    /**
     * Sets a color in the collection of available colors. Allows the user to add a new color if the
     * index is one greater than the highest index of available colors or overwrite a current
     * available color by using a current color index
     */
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

    /**
     * Parses a string entered by the user, and processes it with the model's interpreter. The
     * interpreter acts upon the given instruction and returns a string if there was an error.
     * 
     * @param s The instruction(s) to be parsed and processed
     * @return An error string if there was an error
     */
    public String parseInput (String s) {
        myUndoStack.push((HashMap<Integer, Turtle>) myTurtles);
        myRedoStack.clear();
        myCommandHistory.add(s);
        String ret = myInterpreter.parseInput(s);
        return ret;
    }

    /**
     * @param id The ID of the desired turtle
     * @return The current x coordinate of the desired turtle
     */
    public double getTurtleX (int id) {
        return getTurtle(id).getX();
    }

    /**
     * @param id The ID of the desired turtle
     * @return The current y coordinate of the desired turtle
     */
    public double getTurtleY (int id) {
        return getTurtle(id).getY();
    }

    /**
     * @param id The ID of the desired turtle
     * @return The current angle in degrees of the desired turtle
     */
    public double getTurtleAngle (int id) {
        return getTurtle(id).getAngle();
    }

    /**
     * @param id The ID of the desired turtle
     * @return The current shape of the desired turtle
     */
    public String getTurtleShape (int id) {
        return myAvailableShapes.get(getTurtle(id).getShapeIndex());
    }

    /**
     * @param id The ID of the desired turtle
     * @return Whether or not the desired turtle is visible or not
     */
    public boolean isTurtleVisible (int id) {
        return getTurtle(id).isVisible();
    }

    /**
     * @param id The ID of the desired turtle
     * @return Whether or not the desired turtle's pen is down or not
     */
    public boolean isTurtleDrawing (int id) {
        return getTurtle(id).isDrawing();
    }

    /**
     * @return The collection of paths of all turtles in the current model, both active and inactive
     */
    public Collection<Path> getTurtlePaths () {
        Collection<Path> list = new ArrayList<Path>();
        for (int id : myTurtles.keySet()) {
            list.addAll(myTurtles.get(id).getPaths());
        }
        return list;
    }

    /**
     * @return The collection of stamps of all turtles in the current model, both active and
     *         inactive
     */
    public Collection<Stamp> getTurtleStamps () {
        Collection<Stamp> list = new ArrayList<Stamp>();
        for (int id : myTurtles.keySet()) {
            list.addAll(myTurtles.get(id).getStamps());
        }
        return list;
    }

    /**
     * Adds a variable to the variable cache
     * 
     * @param variableName Name of the variable to be added to the variable cache
     * @param variableValue Value of the variable to be added to the variable cache
     * @return An error string if there was an error
     */
    public String putVariable (String variableName, String variableValue) {
        return myVariableCache.put(variableName, variableValue);
    }

    /**
     * @return The contents of the variable cache in a map with format <variableName, variableValue>
     */
    public Map<String, String> getAllVariables () {
        return myVariableCache.getKeyValuePairs();
    }

    /**
     * @return The contents of the command cache in a map with format <commandName, command>
     */
    public Map<String, String> getAllCommands () {
        return myCommandCache.getAllCommands();
    }

    /**
     * @return The contents of the command history in a list
     */
    public List<String> getHistory () {
        return myCommandHistory.getHistory();
    }

    /**
     * Reads a library file with variables and commands with the given desired file name. Commands
     * in file must all be in English, but will reset model's language to whatever it was previous
     * to reading in the file
     * 
     * @param filename The file to be read. Must be an absolute path to the file
     * @return An error string if there was an error
     */
    public String readLibrary (String filename) {
        String oldLanguage = myLanguage;
        try {
            Scanner sc = new Scanner(new File(filename));
            setLanguage("English");
            String toBeParsed = "";
            while (sc.hasNext()) {
                toBeParsed = toBeParsed + sc.next() + " ";
            }
            sc.close();
            parseInput(toBeParsed);
            setLanguage(oldLanguage);
        }
        catch (Exception e) {
            return FileNotFound.MESSAGE;
        }
        return null;
    }

    /**
     * Saves a library file with variables and commands to be used later with the given desired file
     * name. Commands to be saved must all be in English
     * 
     * @param filename The absolute path to the file to be saved
     * @return An error string if there was an error
     */
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

    /**
     * @return The list of all available languages
     */
    public List<String> getAvailableLanguages () {
        return myAvailableLanguages;
    }

    /**
     * Sets the current language of the model
     * 
     * @param language The language to set the model to
     * @return An error string if an error occurred
     */
    public String setLanguage (String language) {
        String ret = myInstructionFactory.setLanguage(language);
        if (ret.isEmpty()) {
            myLanguage = language;
        }
        return ret;
    }

    /**
     * @return True if the user can undo their previous action, false if they can't
     */
    public boolean canUndo () {
        return !myUndoStack.isEmpty();
    }

    /**
     * Undoes the user's previous turtle action
     * 
     * @return An error string if there was an error
     */
    public String undo () {
        final String UNDO_ERROR = "Can't undo";
        if (canUndo()) {
            myRedoStack.push((HashMap<Integer, Turtle>) myTurtles);
            HashMap<Integer, Turtle> undoTurtles = myUndoStack.pop();
            myTurtles = undoTurtles;
            return "";
        }
        else {
            return UNDO_ERROR;
        }
    }

    /**
     * @return True if the user can redo a previously undone command, false if they can't
     */
    public boolean canRedo () {
        return !myRedoStack.isEmpty();
    }

    /**
     * Redoes a turtle command the user previous undid
     * 
     * @return An error string if there was an error
     */
    public String redo () {
        final String REDO_ERROR = "Can't redo";
        if (canRedo()) {
            myUndoStack.push((HashMap<Integer, Turtle>) myTurtles);
            HashMap<Integer, Turtle> redoTurtles = myRedoStack.pop();
            myTurtles = redoTurtles;
            return "";
        }
        else {
            return REDO_ERROR;
        }
    }

    /**
     * @return A collection of the ID's of currently active turtles
     */
    public Collection<Integer> getActiveTurtleIDs () {
        Collection<Integer> list = new ArrayList<Integer>();
        for (int id : myTurtles.keySet()) {
            if (myTurtles.get(id).isActive()) {
                list.add(id);
            }
        }
        return list;
    }

    /**
     * @return A collection of the ID's of all turtles in the model
     */
    public Collection<Integer> getAllTurtleIDs () {
        Collection<Integer> list = new ArrayList<Integer>();
        for (int id : myTurtles.keySet()) {
            list.add(id);
        }
        return list;
    }

    /**
     * Changes the background color of the model to the given color index if the index exists
     * 
     * @param colorIndex The index of the desired color to change to
     * @return An error string if there was an error
     */
    public String setBGColor (int colorIndex) {
        myCommandHistory.add("SETBG " + colorIndex);
        if (colorIndex < 0 || colorIndex >= myAvailableColors.size()) { return "Index is out of range"; }
        myBGColorIndex = colorIndex;
        return "";
    }

    /**
     * @return The current background color of the model
     */
    public Color getBGColor () {
        return myAvailableColors.get(myBGColorIndex);
    }

    /**
     * Sets the pen color of current active turtles to the given color index if the index exists
     * 
     * @param colorIndex The index of the desired color to change to
     * @return An error string if there was an error
     */
    public String setPenColor (int colorIndex) {
        myCommandHistory.add("SETPC " + colorIndex);
        if (colorIndex < 0 || colorIndex >= myAvailableColors.size()) { return "Index is out of range"; }
        myPenColorIndex = colorIndex;
        for (Turtle t : getActiveTurtles()) {
            t.setColor(myAvailableColors.get(colorIndex));
        }
        return "";
    }

    /**
     * @return The current pen color of active turtles
     */
    public Color getPenColor () {
        return myAvailableColors.get(myPenColorIndex);
    }

    /**
     * Sets the pen size in pixels of currently active turtles
     * 
     * @param pixels The desired pen size in pixels to set to
     * @return An error string if there was an error
     */
    public String setPenSize (int pixels) {
        myCommandHistory.add("SETPS " + pixels);
        if (pixels < 0) { return "Pen size must be non-negative"; }
        myPenSize = pixels;
        for (Turtle t : getActiveTurtles()) {
            t.setPenSize(pixels);
        }
        return "";
    }

    /**
     * @return The current pen size of active turtles
     */
    public int getPenSize () {
        return myPenSize;
    }

    /**
     * @return A list of available colors in the current model
     */
    public List<Color> getAvailableColors () {
        return myAvailableColors;
    }

    /**
     * @return The current shape index of active turtles
     */
    public String getShape () {
        return myAvailableShapes.get(myShapeIndex);
    }

    /**
     * Sets the shape index for currently active turtles
     * 
     * @param shapeIndex The desired shape index to set currently active turtles to
     * @return An error string if there was an error
     */
    public String setShape (int shapeIndex) {
        myCommandHistory.add("SETSH " + shapeIndex);
        if (shapeIndex < 0 || shapeIndex >= myAvailableShapes.size()) { return "Index is out of range"; }
        myShapeIndex = shapeIndex;
        for (Turtle t : getActiveTurtles()) {
            t.setShape(shapeIndex);
        }
        return "";
    }

    /**
     * @return The current background color of the model
     */
    public int getBGColorIndex () {
        return myBGColorIndex;
    }

    /**
     * @return The index of the current pen color used by active turtles
     */
    public int getPenColorIndex () {
        return myPenColorIndex;
    }

    /**
     * @return The index of the current shape used by active turtles
     */
    public int getShapeIndex () {
        return myShapeIndex;
    }

    /**
     * @return A list of available shapes in the current model
     */
    public List<String> getAvailableShapes () {
        return myAvailableShapes;
    }

    /**
     * Runs the ONKEY command if the correct key is pressed and an ONKEY command exists
     * 
     * @param keyCode The keycode of the key pressed by the user
     * @return An error string if there was an error
     */
    public String keyPressed (int keyCode) {
        if (myCommandCache.contains("ONKEY")) { return parseInput("ONKEY " + keyCode); }
        return "";
    }

    /**
     * Runs the ONCLICK command if the mouse is clicked and an ONCLICK command exists
     * 
     * @param x The x coordinate of the mouse
     * @param y The y coordinate of the mouse
     * @return An error string if there was an error
     */
    public String mouseClicked (int x, int y) {
        if (myCommandCache.contains("ONCLICK")) { return parseInput("ONCLICK " + x + " " + y); }
        return "";
    }

    /**
     * Runs the ONMOVE command if the mouse is moved in the model and an ONMOVE command exists
     * 
     * @param x The x coordinate of the mouse
     * @param y The y coordinate of the mouse
     * @return An error string if there was an error
     */
    public String mouseMoved (int x, int y) {
        if (myCommandCache.contains("ONMOVE")) { return parseInput("ONMOVE " + x + " " + y); }
        return "";
    }
}
