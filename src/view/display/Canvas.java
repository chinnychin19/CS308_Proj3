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
 * Class that displays the turtle sprites using JGEngine
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 */

public class Canvas extends JGEngine implements UpdatableDisplay {
    // private ViewController myController;
    private String myImageName = "Turtle1_1.gif";
    private String myError = "";
    private Collection<Path> myPathList = new ArrayList<Path>();
    private JGColor myPenColor = JGColor.red;
    private boolean myGridStatus = false;
    private boolean myTurtleStatus = true;
    private boolean myMouseClicked = false;
    private boolean myHighlightsOn = false;
    private int myX = 0;
    private int myY = 0;

    private Map<Integer, TurtleSprite> myTurtleMap = new HashMap<Integer, TurtleSprite>();
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
        // myController = controller;
    }

    @Override
    public void initCanvas () {
        setCanvasSettings(Constants.X_TILES, Constants.Y_TILES, Constants.TILE_HEIGHT,
                          Constants.TILE_HEIGHT, null, Constants.CANVAS_COLOR, null);
    }

    @Override
    public void initGame () {
        setFrameRate(Constants.FRAMES_PER_SECOND, 2);
        defineImage(Constants.IMAGE_NAME + 1, "-", Constants.TURTLE_CID, myImageName, "-", 0, 0, 0, 0);

        TurtleSprite myTurtle = new TurtleSprite(this, Constants.CANVAS_WIDTH / 2 -
                                                       Constants.TURTLE_OFFSET,
                                                 Constants.CANVAS_HEIGHT / 2 -
                                                         Constants.TURTLE_OFFSET, 1,
                                                         Constants.IMAGE_NAME + 1);
        myTurtleMap.put(1, myTurtle);
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
            for (int ID : myActiveTurtleIDs) {
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
            if (!myTurtleMap.containsKey(ID)) {
                addNewTurtle(ID);
            }

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
        int index = 1;

        if (angle >= 135 && angle < 225) {
            index = 2;
        }

        else if (angle >= 225 && angle < 315) {
            index = 3;
        }

        else if (angle >= 315 || angle < 45) {
            index = 4;
        }

        return imageName.substring(0, Constants.SHAPE_NAME_LENGTH) + "_" + index + ".gif";
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

        toMove.setPos(forceWithinBounds(x) + Constants.CANVAS_WIDTH / 2 -
                      Constants.TURTLE_OFFSET,
                      -forceWithinBounds(y) + Constants.CANVAS_HEIGHT / 2 - Constants.TURTLE_OFFSET);
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
     * Method that ensures turtle stays within bounds of Canvas
     * 
     * @param coordinate
     * @return coordinate within bounds
     */
    public double forceWithinBounds (double x) {
        if (x > Constants.CANVAS_WIDTH / 2) {
            x = (x % (Constants.CANVAS_WIDTH / 2)) - (Constants.CANVAS_WIDTH / 2);
        }

        else if (x < -Constants.CANVAS_WIDTH / 2) {
            x = Constants.CANVAS_WIDTH / 2 - (Math.abs(x) % (Constants.CANVAS_WIDTH / 2));
        }
        return x;
    }

    /**
     * Method that draws stamps
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
        ArrayList<Integer> activeTurtleList = getActiveTurtles();
        Map<Integer, String> turtleShapeList = getTurtleShape();
        Map<Integer, Double> turtleXMap = getTurtleX();
        Map<Integer, Double> turtleYMap = getTurtleY();
        Map<Integer, Double> turtleAngleMap = getTurtleAngle();
        Map<Integer, Boolean> turtleVisibilityMap = getTurtleVisibility();
        Collection<Path> paths = myCurrentModel.getTurtlePaths();
        Collection<Stamp> stamps = myCurrentModel.getTurtleStamps();
        Color pen = myCurrentModel.getPenColor();
        Color bg = myCurrentModel.getBGColor();
        myTurtleStamps = stamps;
        myError = error;
        myActiveTurtleIDs = activeTurtleList;

        changeBackgroundColor(colorToJGColor(bg));
        changePenColor(colorToJGColor(pen));

        for (Integer ID : activeTurtleList) {
            if (!myTurtleMap.containsKey(ID)) {
                addNewTurtle(ID);
            }

            if (myImageName.substring(0, Constants.SHAPE_NAME_LENGTH).equals(myCurrentModel.getShape())) {
                myImageName = myCurrentModel.getShape() + "_1.gif";
            }

            changeTurtleImage(ID, turtleShapeList.get(ID));
            setAngle(ID, turtleAngleMap.get(ID), turtleShapeList.get(ID));
            moveTurtle(ID, turtleXMap.get(ID), turtleYMap.get(ID));
            changeTurtleVisiblity(ID, turtleVisibilityMap.get(ID));
        }

        myTurtleStamps = stamps;
        myPathList = paths;

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
    private ArrayList<Integer> getActiveTurtles () {
        return (ArrayList<Integer>) myCurrentModel.getActiveTurtleIDs();
    }

    /**
     * Method that returns Map between turtle ID and X position
     * 
     * @return Map that maps turtle ID (integer) to turtle X position (double)
     */
    private Map<Integer, Double> getTurtleX () {
        Map<Integer, Double> turtleXMap = new HashMap<Integer, Double>();
        ArrayList<Integer> activeTurtleList = getActiveTurtles();
        for (Integer ID : activeTurtleList) {
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
        ArrayList<Integer> activeTurtleList = getActiveTurtles();
        for (Integer ID : activeTurtleList) {
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
        ArrayList<Integer> activeTurtleList = getActiveTurtles();
        for (Integer ID : activeTurtleList) {
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
        ArrayList<Integer> activeTurtleList = getActiveTurtles();
        for (Integer ID : activeTurtleList) {
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
        ArrayList<Integer> activeTurtleList = getActiveTurtles();
        Map<Integer, String> activeTurtleShapes = new HashMap<Integer, String>();
        for (Integer ID : activeTurtleList) {
            activeTurtleShapes.put(ID, myCurrentModel.getTurtleShape(ID));
        }
        return activeTurtleShapes;
    }

}
