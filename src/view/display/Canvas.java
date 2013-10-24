package view.display;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import model.Model;
import model.Path;
import view.Constants;
import view.UpdatableDisplay;
import view.ViewController;
import jgame.JGColor;
import jgame.JGFont;
import jgame.platform.JGEngine;
import model.Stamp;


/**
 * Class that displays the turtle sprites
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 */

public class Canvas extends JGEngine implements UpdatableDisplay {
    private String myImageName = Constants.IMAGE_FULL_NAME;
    private String myError = Constants.INITIAL_ERROR;
    private Collection<Path> myPathList = new ArrayList<Path>();
    private JGColor myPenColor = Constants.PEN_COLOR;
    private boolean myGridStatus = false;
    private boolean myTurtleStatus = true;
    private boolean myMouseClicked = false;
    private boolean myHighlightsOn = false;
    private int myX = 0;
    private int myY = 0;

    private Map<Integer, TurtleSprite> myTurtleMap = new HashMap<Integer, TurtleSprite>();
    private ArrayList<Integer> myTurtleIDs = new ArrayList<Integer>();
    private ArrayList<Integer> myActiveTurtleIDs = new ArrayList<Integer>();
    private Collection<Stamp> myTurtleStamps = new ArrayList<Stamp>();
    private Model myCurrentModel;

    /**
     * Constructor for Canvas class
     * 
     * @param model Model
     */
    public Canvas (Model model) {
        initEngineComponent(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
        myCurrentModel = model;
    }

    /**
     * Method that adds a Viewcontroller to the class
     * 
     * @param controller
     */
    public void addController (ViewController controller) {
        setEngineController(controller);
    }

    @Override
    public void initCanvas () {
        setCanvasSettings(Constants.X_TILES, Constants.Y_TILES, Constants.TILE_HEIGHT,
                          Constants.TILE_HEIGHT, null, Constants.CANVAS_COLOR, null);
    }

    @Override
    public void initGame () {
        setFrameRate(Constants.FRAMES_PER_SECOND, 2);
        defineImage(Constants.IMAGE_NAME + 1, "-", Constants.TURTLE_CID, myImageName, "-", 0, 0, 0,
                    0);

        TurtleSprite myTurtle = new TurtleSprite(this, Constants.CANVAS_WIDTH / 2 -
                                                       Constants.TURTLE_OFFSET,
                                                 Constants.CANVAS_HEIGHT / 2 -
                                                         Constants.TURTLE_OFFSET, 1,
                                                 Constants.IMAGE_NAME + 1);
        myTurtleMap.put(1, myTurtle);
        myTurtleIDs.add(1);
        myActiveTurtleIDs.add(1);
    }

    @Override
    public void doFrame () {
        handleMouseClick();
        handleMove();
    }

    @Override
    public void paintFrame () {
        super.paintFrame();

        if (myGridStatus) {
            drawGrid();
        }

        if (myTurtleStatus) {
            drawStatus();
        }

        drawStamps();
        drawPath();
        displayError(myError);

        if (myHighlightsOn) {
            for (int ID : myTurtleIDs) {
                if (!myTurtleMap.get(ID).isSuspended()) {
                    highlightTurtle(ID);
                }
            }
        }

    }

    /**
     * Method that paints the turtle status on the canvas
     */
    private void drawStatus () {
        int offset = 0;

        for (int ID : myActiveTurtleIDs) {
            TurtleSprite currentTurtle = myTurtleMap.get(ID);

            drawString("Turtle " + ID, Constants.MARGIN, offset += Constants.LINE_OFFSET, -1,
                       new JGFont("arial", 0, Constants.FONT_SIZE),
                       myPenColor);
            drawString("X: " + (currentTurtle.getOffsetX() - Constants.CANVAS_WIDTH / 2),
                       Constants.MARGIN,
                       offset += Constants.LINE_OFFSET, -1,
                       new JGFont("arial", 0, Constants.FONT_SIZE),
                       myPenColor);
            drawString("Y: " + (-currentTurtle.getOffsetY() + Constants.CANVAS_HEIGHT / 2),
                       Constants.MARGIN, offset +=
                               Constants.LINE_OFFSET,
                       -1,
                       new JGFont("arial", 0, Constants.FONT_SIZE),
                       myPenColor);
            drawString("Heading: " + currentTurtle.getHeading(), Constants.MARGIN,
                       offset += Constants.LINE_OFFSET, -1,
                       new JGFont("arial", 0, Constants.FONT_SIZE),
                       myPenColor);

            offset += Constants.LINE_OFFSET;
        }

    }

    /**
     * Method that sends coordinates of mouse clicking to Model
     */
    private void handleMouseClick () {
        if (getMouseButton(1)) {
            myMouseClicked = true;

        }

        else if (!getMouseButton(1) && myMouseClicked) {
            myMouseClicked = false;
            myController.onClick((getMouseX() - Constants.CANVAS_WIDTH / 2), (-getMouseY() +
                                 Constants.CANVAS_HEIGHT / 2));
        }
    }

    /**
     * Method that deals with "ONMOVE" command
     */
    private void handleMove () {
        if ((getMouseX() != myX || getMouseY() != myY) &&
            myCurrentModel.getCommandCache().contains("ONMOVE")) {
            myX = getMouseX();
            myY = getMouseY();
            myController.onMove((getMouseX() - Constants.CANVAS_WIDTH / 2), (-getMouseY() +
                                Constants.CANVAS_HEIGHT / 2));
        }
    }

    /**
     * 
     * @param ID Turtle ID
     * @param visible visibility status of turtle
     */
    private void changeTurtleVisiblity (int ID, boolean visible) {
        TurtleSprite tempTurtle = myTurtleMap.get(ID);

        if (visible == tempTurtle.isSuspended()) {
            if (visible) {
                tempTurtle.resume();
            }

            else {
                tempTurtle.suspend();
            }
        }

    }

    /**
     * Method that draws the turtle's path from Model's stored list of paths
     */
    private void drawPath () {
        for (int i = 0; i < myPathList.size(); i++) {
            Path toDraw = ((ArrayList<Path>) myPathList).get(i);
            drawLine(toDraw.getX1() + Constants.CANVAS_WIDTH / 2, -toDraw.getY1() +
                                                                  Constants.CANVAS_WIDTH / 2,
                     toDraw.getX2() + Constants.CANVAS_WIDTH / 2, -toDraw.getY2() +
                                                                  Constants.CANVAS_WIDTH / 2,
                     toDraw.getPenSize(),
                     colorToJGColor(toDraw.getColor()));

        }
    }

    /**
     * Method that converts Color to JGColor
     * 
     * @param c Color
     * @return JGColor of color
     */
    private JGColor colorToJGColor (Color c) {
        return new JGColor(c.getRed(), c.getGreen(), c.getBlue());
    }

    /**
     * Method to display error returned from Model on canvas
     * 
     * @param error
     */
    private void displayError (String error) {
        drawString(error, Constants.CANVAS_WIDTH / 2, Constants.CANVAS_HEIGHT *
                                                      Constants.ERROR_MESSAGE_OFFSET, 0,
                   new JGFont("arial", 0, Constants.FONT_SIZE), JGColor.red);
    }

    /**
     * Method that changes turtle image
     * 
     * @param ID turtle ID
     * @param imageName current turtle image name
     */
    public void changeTurtleImage (int ID, String imageName) {
        String newName = adjustImageAngle(imageName, myTurtleMap.get(ID).getHeading());
        defineImage(Constants.IMAGE_NAME + ID, newName);
    }

    /**
     * Method that sets heading of turtle
     * 
     * @param ID turtle ID
     * @param angle new angle of turtle
     * @param imageName current turtle image name
     */
    public void setAngle (int ID, double angle, String imageName) {
        if (myTurtleMap.get(ID).getHeading() != angle) {
            myTurtleMap.get(ID).setHeading(angle);
            String newName = adjustImageAngle(imageName, angle);
            defineImage(Constants.IMAGE_NAME + ID, newName);
        }

    }

    /**
     * Method that changes imagename depending on turtle angle
     * 
     * @param imageName name of image file
     * @param angle angle of turtle
     * @return name of image file corrected for angle
     */
    private String adjustImageAngle (String imageName, double angle) {
        int index = Constants.INDEX_1;

        if (angle >= Constants.Q1 && angle < Constants.Q2) {
            index = Constants.INDEX_2;
        }

        else if (angle >= Constants.Q2 && angle < Constants.Q3) {
            index = Constants.INDEX_3;
        }

        else if (angle >= Constants.Q3 || angle < Constants.Q4) {
            index = Constants.INDEX_4;
        }

        return imageName.substring(0, Constants.SHAPE_NAME_LENGTH) + "_" + index +
               Constants.IMAGE_SUFFIX;
    }

    /**
     * Method that defines a new image within JGEngine
     * 
     * @param name name of image
     * @param imageName name of image file
     */
    private void defineImage (String name, String imageName) {
        defineImage(name, "-", Constants.TURTLE_CID, imageName, "-", 0, 0, 0,
                    0);
    }

    /**
     * Method that moves turtle
     * 
     * @param ID ID of turtle
     * @param x x position of turtle
     * @param y y position of turtle
     */
    private void moveTurtle (int ID, double x, double y) {
        TurtleSprite toMove = myTurtleMap.get(ID);

        toMove.setPos(x + Constants.CANVAS_WIDTH / 2 -
                      Constants.TURTLE_OFFSET,
                      -y + Constants.CANVAS_HEIGHT / 2 - Constants.TURTLE_OFFSET);
    }

    /**
     * Changes color of canvas background
     * 
     * @param color new background color
     */
    private void changeBackgroundColor (JGColor color) {
        setBGColor(color);
        setBGImage(null);
    }

    /**
     * Method that changes the pen's color
     * 
     * @param color new pen color
     */
    public void changePenColor (JGColor color) {
        myPenColor = color;
    }

    /**
     * Method that draws grid
     */
    public void drawGrid () {
        for (int i = 1; i < Constants.NUM_GRIDLINES; i++) {
            drawLine(0, Constants.CANVAS_HEIGHT * i / Constants.NUM_GRIDLINES,
                     Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT * i / Constants.NUM_GRIDLINES);
        }

        for (int j = 1; j < Constants.NUM_GRIDLINES; j++) {
            drawLine(Constants.CANVAS_WIDTH * j / Constants.NUM_GRIDLINES, 0,
                     Constants.CANVAS_WIDTH * j / Constants.NUM_GRIDLINES,
                     Constants.CANVAS_HEIGHT * 2);
        }
    }

    /**
     * Method that draws list of stamps
     */
    private void drawStamps () {
        for (int i = 0; i < myTurtleStamps.size(); i++) {
            Stamp s = ((ArrayList<Stamp>) myTurtleStamps).get(i);
            String newName = adjustImageAngle(s.getShape(), s.getAngle());
            defineImage("" + i, newName);
            drawImage("" + i, s.getX() + Constants.CANVAS_WIDTH / 2 - Constants.TURTLE_OFFSET,
                      -s.getY() + Constants.CANVAS_HEIGHT / 2 - Constants.TURTLE_OFFSET);
        }
    }

    /**
     * Method to highlight an active turtle
     * 
     * @param ID ID of active turtle
     */
    private void highlightTurtle (int ID) {
        double x = myTurtleMap.get(ID).x;
        double y = myTurtleMap.get(ID).y;
        drawLine(x, y, x + 50, y, 1, JGColor.green);
        drawLine(x + 50, y, x + 50, y + 50, 1, JGColor.green);
        drawLine(x + 50, y + 50, x, y + 50, 1, JGColor.green);
        drawLine(x, y + 50, x, y, 1, JGColor.green);
    }

    /**
     * Adds new turtle at origin. This method is only called if myTurtleMap does not have the ID as
     * a key
     * 
     * @param ID ID of new turtle
     */
    private void addNewTurtle (int ID) {
        defineImage(Constants.IMAGE_NAME + ID, myImageName);

        TurtleSprite myTurtle =
                new TurtleSprite(this, Constants.CANVAS_WIDTH / 2 - Constants.TURTLE_OFFSET,
                                 Constants.CANVAS_HEIGHT / 2 -
                                         Constants.TURTLE_OFFSET, 1,
                                 Constants.IMAGE_NAME + ID);
        myTurtleMap.put(ID, myTurtle);
    }

    /**
     * Method that sets boolean for whether grid should be drawn
     * 
     * @param gridStatus new status of grid
     */
    public void setGridStatus (boolean gridStatus) {
        myGridStatus = gridStatus;

    }

    /**
     * Method that sets boolean for whether active turtle status is displayed on the canvas
     * 
     * @param turtleStatus new turtle status
     */
    public void setTurtleStatus (boolean turtleStatus) {
        myTurtleStatus = turtleStatus;
    }

    /**
     * Method that sets boolean for whether active turtles should be highlighted
     * 
     * @param highlightsOn new highlighting status
     */
    public void setHighlights (boolean highlightsOn) {
        myHighlightsOn = highlightsOn;
    }

    @Override
    public void updateDisplay (String error) {
        myError = error;
        myTurtleIDs = getAllTurtles();
        myActiveTurtleIDs = getActiveTurtles();
        myTurtleStamps = myCurrentModel.getTurtleStamps();
        myPathList = myCurrentModel.getTurtlePaths();
        Color pen = myCurrentModel.getPenColor();
        Color bg = myCurrentModel.getBGColor();
        Map<Integer, String> turtleShapeList = getTurtleShape();
        Map<Integer, Double> turtleXMap = getTurtleX();
        Map<Integer, Double> turtleYMap = getTurtleY();
        Map<Integer, Double> turtleAngleMap = getTurtleAngle();
        Map<Integer, Boolean> turtleVisibilityMap = getTurtleVisibility();

        changeBackgroundColor(colorToJGColor(bg));
        changePenColor(colorToJGColor(pen));

        for (Integer ID : myTurtleIDs) {
            if (!myTurtleMap.containsKey(ID)) {
                addNewTurtle(ID);
            }
            changeTurtleImage(ID, turtleShapeList.get(ID));
            setAngle(ID, turtleAngleMap.get(ID), turtleShapeList.get(ID));
            moveTurtle(ID, turtleXMap.get(ID), turtleYMap.get(ID));
            changeTurtleVisiblity(ID, turtleVisibilityMap.get(ID));
        }

    }

    @Override
    public void changeModel (Model model) {
        myCurrentModel = model;
    }

    /**
     * Method that returns an ArrayList of active turtles
     * 
     * @return ArrayList of currently active turtles
     */
    private ArrayList<Integer> getAllTurtles () {
        return (ArrayList<Integer>) myCurrentModel.getAllTurtleIDs();
    }

    private ArrayList<Integer> getActiveTurtles () {
        return (ArrayList<Integer>) myCurrentModel.getActiveTurtleIDs();
    }

    /**
     * Clears turtleMap
     */
    public void clearTurtleMap () {
        for (int ID : myTurtleMap.keySet()) {
            myTurtleMap.get(ID).remove();
        }
        myTurtleMap.clear();
    }

    /**
     * Method that returns Map between turtle ID and X position
     * 
     * @return Map that maps turtle ID (integer) to turtle X position (double)
     */
    private Map<Integer, Double> getTurtleX () {
        Map<Integer, Double> turtleXMap = new HashMap<Integer, Double>();
        ArrayList<Integer> turtleList = getAllTurtles();
        for (Integer ID : turtleList) {
            turtleXMap.put(ID, myCurrentModel.getTurtleX(ID));
        }
        return turtleXMap;
    }

    /**
     * Method that returns Map between turtle ID and Y position
     * 
     * @return Map that maps turtle ID (integer) to turtle y position (double)
     */
    private Map<Integer, Double> getTurtleY () {
        Map<Integer, Double> turtleYMap = new HashMap<Integer, Double>();
        ArrayList<Integer> turtleList = getAllTurtles();
        for (Integer ID : turtleList) {
            turtleYMap.put(ID, myCurrentModel.getTurtleY(ID));
        }
        return turtleYMap;
    }

    /**
     * Method that returns Map between turtle ID and angle
     * 
     * @return Map that maps turtle ID (integer) to turtle angle (double)
     */
    private Map<Integer, Double> getTurtleAngle () {
        Map<Integer, Double> turtleAngleMap = new HashMap<Integer, Double>();
        ArrayList<Integer> turtleList = getAllTurtles();
        for (Integer ID : turtleList) {
            turtleAngleMap.put(ID, myCurrentModel.getTurtleAngle(ID));
        }
        return turtleAngleMap;
    }

    /**
     * Method that returns Map between turtle ID and visibility
     * 
     * @return Map that maps turtle ID (integer) to turtle visibility (boolean)
     */
    private Map<Integer, Boolean> getTurtleVisibility () {
        Map<Integer, Boolean> turtleVisibilityMap = new HashMap<Integer, Boolean>();
        ArrayList<Integer> turtleList = getAllTurtles();
        for (Integer ID : turtleList) {
            turtleVisibilityMap.put(ID, myCurrentModel.isTurtleVisible(ID));
        }
        return turtleVisibilityMap;
    }

    /**
     * Method that returns Map between turtle ID and shape
     * 
     * @return Map that maps turtle ID (integer) to turtle shape (String)
     */
    private Map<Integer, String> getTurtleShape () {
        ArrayList<Integer> turtleList = getAllTurtles();
        Map<Integer, String> activeTurtleShapes = new HashMap<Integer, String>();
        for (Integer ID : turtleList) {
            activeTurtleShapes.put(ID, myCurrentModel.getTurtleShape(ID));
        }
        return activeTurtleShapes;
    }

}
