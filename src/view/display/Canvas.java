package view.display;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import model.Path;
import view.Constants;
import jgame.JGColor;
import jgame.JGFont;
import jgame.JGPoint;
import jgame.platform.JGEngine;


/**
 * Class that displays the turtle sprite(s) within the canvas.
 * 
 * @author susanzhang93
 * 
 */
public class Canvas extends JGEngine implements CanvasObserver {
    private String myImageName = "Turtle1_1.gif";
    private String myError = "";
    private Collection<Path> myPointList = new ArrayList<Path>();
    private JGColor myPenColor = JGColor.red;
    private boolean myGridStatus = false;
    private boolean myTurtleStatus = true;
    private boolean myMouseClicked = false;
    private int myPenSize = 1;

    // IMPLEMENTATION 2
    private Map<Integer, TurtleSprite> myTurtleMap = new HashMap<Integer, TurtleSprite>();
    private ArrayList<Integer> myActiveTurtleIDs = new ArrayList<Integer>();
    private int stampCounter = 0;
    private String image = "turtleGif";

    public static void main (String[] args) {
        new Canvas(new JGPoint(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT));
    }

    public Canvas () {
        initEngineComponent(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
    }

    public Canvas (JGPoint size) {
        initEngine(size.x, size.y);
    }

    @Override
    public void initCanvas () {
        setCanvasSettings(Constants.X_TILES, Constants.Y_TILES, Constants.TILE_HEIGHT,
                          Constants.TILE_HEIGHT, null, Constants.CANVAS_COLOR, null);
    }

    @Override
    public void initGame () {
        setFrameRate(Constants.FRAMES_PER_SECOND, 2);
        setPFWrap(true, true, 0, 0);
        // defineImage(image + 1, "-", Constants.TURTLE_CID, myImageName, "-", 0, 0, 50, 50);
        //
        // TurtleSprite myTurtle = new TurtleSprite(this, Constants.CANVAS_WIDTH / 2 -
        // Constants.TURTLE_OFFSET,
        // Constants.CANVAS_HEIGHT / 2 -
        // Constants.TURTLE_OFFSET, 1,
        // image + 1);
        // myTurtleMap.put(1, myTurtle);
        // myActiveTurtleIDs.add(1);
    }

    @Override
    public void doFrame () {
        handleMouseClick();
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

        displayError(myError);

        if (getKey('S')) {
            stamp();
        }

        if (getKey('C')) {
            clearStamps();
        }

        if (getKey('X')) {
            changeTurtleImage("Turtle2.gif");
        }

        for (int ID : myActiveTurtleIDs) {
            if (!myTurtleMap.get(ID).isSuspended()) {
                highlightTurtle(ID);
            }
        }

        drawPath();
    }

    private void drawStatus () {
        int offset = 0;

        for (int ID : myActiveTurtleIDs) {

            TurtleSprite currentTurtle = myTurtleMap.get(ID);

            drawString("Turtle " + ID, 5, offset += 13, -1, new JGFont("arial", 0, 12),
                       myPenColor);
            drawString("X: " + (currentTurtle.getOffsetX() - Constants.CANVAS_WIDTH / 2), 5,
                       offset += 13, -1,
                       new JGFont("arial", 0, 12),
                       myPenColor);
            drawString("Y: " + (-currentTurtle.getOffsetY() + Constants.CANVAS_HEIGHT / 2),
                       5, offset +=
                               13,
                       -1,
                       new JGFont("arial", 0, 12),
                       myPenColor);
            drawString("Heading: " + currentTurtle.getHeading(), 5, offset += 13, -1,
                       new JGFont("arial", 0, 12),
                       myPenColor);
        }

    }

    /**
     * Sends coordinates of mouse clicking
     */
    private void handleMouseClick () {
        if (getMouseButton(1)) {
            myMouseClicked = true;
            System.out.println((getMouseX() - Constants.CANVAS_WIDTH / 2) + " " + (-getMouseY() +
                               Constants.CANVAS_HEIGHT / 2)); // TODO: How to ensure only once?
        }

        else if (!getMouseButton(1) && myMouseClicked) {
            myMouseClicked = false;
        }
    }

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
        for (int i = 0; i < myPointList.size(); i++) {
            Path toDraw = ((ArrayList<Path>) myPointList).get(i);
            // splitLine(toDraw);
            drawLine(toDraw.getX1() + Constants.CANVAS_WIDTH / 2, -toDraw.getY1() +
                                                                  Constants.CANVAS_WIDTH / 2,
                     toDraw.getX2() + Constants.CANVAS_WIDTH / 2, -toDraw.getY2() +
                                                                  Constants.CANVAS_WIDTH / 2,
                     myPenSize,
                     myPenColor);

        }
    }

    /**
     * Method to display error
     * 
     * @param error
     */
    private void displayError (String error) {
        drawString(error, Constants.CANVAS_WIDTH / 2, Constants.CANVAS_HEIGHT *
                                                      Constants.ERROR_MESSAGE_OFFSET, 0,
                   new JGFont("arial", 0, 12), JGColor.red);
    }

    /**
     * Method that changes turtle image
     * 
     * @param imageName name of image
     */
    public void changeTurtleImage (String imageName) {

        myImageName = imageName;
        // defineImage("turtleGif", "-", Constants.TURTLE_CID, myImageName, "-", 0, 0, 50, 50);

        for (int ID : myActiveTurtleIDs) {
            adjustImageAngle(ID, myTurtleMap.get(ID).getHeading());
        }

    }

    private void moveTurtle (int ID, double x, double y) {
        TurtleSprite toMove = myTurtleMap.get(ID);

        // toMove.setPos(forceWithinBounds(x) + Constants.CANVAS_WIDTH / 2 -
        // Constants.TURTLE_OFFSET,
        // -forceWithinBounds(y) + Constants.CANVAS_HEIGHT / 2 - Constants.TURTLE_OFFSET);

        toMove.setPos(x + Constants.CANVAS_WIDTH / 2 - Constants.TURTLE_OFFSET,
                      -y + Constants.CANVAS_HEIGHT / 2 - Constants.TURTLE_OFFSET);
    }

    /**
     * Moves turtle sprite, and draws line between displacement
     * 
     * @param x new x location of turtle
     * @param y new y location of turtle
     */
    @Deprecated
    public void moveTurtle (double x, double y) {
        myTurtleMap.get(1).setPos(x + Constants.CANVAS_WIDTH / 2 - Constants.TURTLE_OFFSET,
                                  -y + Constants.CANVAS_HEIGHT / 2 - Constants.TURTLE_OFFSET);
    }

    /**
     * Changes color of canvas background
     * 
     * @param color
     */
    private void changeBackgroundColor (JGColor color) {
        setBGColor(color);
        setBGImage(null);
    }

    /**
     * Method that changes the pen's color
     * 
     * @param color color to change pen to
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
     * Sets list of points
     * 
     * @param list new ArrayList of points
     */
    public void setPaths (Collection<Path> list) {
        myPointList = list;
    }

    /**
     * Sets heading of turtle
     */
    public void setHeading (int ID, double newHeading) {

        if (myTurtleMap.get(ID).getHeading() != newHeading) {
            myTurtleMap.get(ID).setHeading(newHeading);
            adjustImageAngle(ID, newHeading);
        }

    }

    // public void splitLine(Path p){
    //
    // if (p.getX1() > Constants.GUI_WIDTH/2){
    // myPointList.add(new Path(p.getX1() % Constants.GUI_WIDTH/2, p.getY1(), p.getX2(),
    // p.getY2()));
    // }
    //
    //
    // }

    public double forceWithinBounds (double x) {
        if (x > Constants.CANVAS_WIDTH / 2) {
            x = (x % (Constants.CANVAS_WIDTH / 2)) - (Constants.CANVAS_WIDTH / 2);
        }

        else if (x < -Constants.CANVAS_WIDTH / 2) {
            x = Constants.CANVAS_WIDTH / 2 - (Math.abs(x) % (Constants.CANVAS_WIDTH / 2));
        }
        return x;
    }

    public void setActiveTurtles (ArrayList<Integer> turtleList) {
        myActiveTurtleIDs = turtleList;
    }

    private void clearStamps () {
        removeObjects(null, Constants.STAMP_CID);
        stampCounter = 0;
    }

    private void stamp () { // TODO: Deal with workspace changint
        for (int ID : myTurtleMap.keySet()) {
            TurtleSprite tempTurtle = myTurtleMap.get(ID);
            String temp = "test" + stampCounter;
            defineImage(temp, "-", Constants.TURTLE_CID, myImageName, "-", 0, 0, 50, 50);
            new TurtleSprite(this, tempTurtle.x, tempTurtle.y, Constants.STAMP_CID,
                             temp);
        }

        stampCounter++;
    }

    private void highlightTurtle (int ID) {// TODO Implement this method!
        double x = myTurtleMap.get(ID).x;
        double y = myTurtleMap.get(ID).y;
        drawLine(x, y, x + 50, y, 1, JGColor.green);
        drawLine(x + 50, y, x + 50, y + 50, 1, JGColor.green);
        drawLine(x + 50, y + 50, x, y + 50, 1, JGColor.green);
        drawLine(x, y + 50, x, y, 1, JGColor.green);
    }

    private void adjustImageAngle (int ID, double angle) { // TODO Make this cleaner/work
        int index = 1;
        if (angle < 45) {
            angle = 360 + angle;
        }

        index = (int) Math.floor((angle - 45) / 90 + 1);

        System.out.println(angle + " " + index);

        myImageName = myImageName.substring(0, 7) + "_" + index + ".gif";

        // if (angle >= 45 && angle < 135) {
        // myImageName = myImageName.substring(0, 7) + "_1.gif";
        // }
        //
        // else if (angle >= 135 && angle < 225) {
        // myImageName = myImageName.substring(0, 7) + "_2.gif";
        // }
        //
        // else if (angle >= 225 && angle < 315) {
        // myImageName = myImageName.substring(0, 7) + "_3.gif";
        // }
        //
        // else if (angle >= 315 || angle < 45) {
        // myImageName = myImageName.substring(0, 7) + "_4.gif";
        // }

        defineImage("turtleGif" + ID, "-", Constants.TURTLE_CID, myImageName, "-", 0, 0, 50,
                    50);
    }

    private void adjustTurtle (ArrayList<Integer> activeTurtleList,
                               Map<Integer, Double> turtleXMap,
                               Map<Integer, Double> turtleYMap,
                               Map<Integer, Double> turtleAngleMap,
                               Map<Integer, Boolean> turtleVisibilityMap,
                               Collection<Path> paths) {
        setActiveTurtles(activeTurtleList);

        for (Integer ID : activeTurtleList) {
            if (!myTurtleMap.containsKey(ID)) {
                defineImage(image + ID, "-", Constants.TURTLE_CID, myImageName, "-", 0, 0, 50, 50);

                TurtleSprite myTurtle =
                        new TurtleSprite(this, turtleXMap.get(ID) + Constants.CANVAS_WIDTH / 2 -
                                               turtleYMap.get(ID) + Constants.TURTLE_OFFSET,
                                         Constants.CANVAS_HEIGHT / 2 -
                                                 Constants.TURTLE_OFFSET, 1,
                                         image + ID);
                myTurtleMap.put(ID, myTurtle);
            }

            moveTurtle(ID, turtleXMap.get(ID), turtleYMap.get(ID));
            setHeading(ID, turtleAngleMap.get(ID));
            changeTurtleVisiblity(ID, turtleVisibilityMap.get(ID));
        }

        setPaths(paths);
    }

    @Override
    public void update (String error,
                        ArrayList<Integer> activeTurtleList,
                        Map<Integer, Double> turtleXMap,
                        Map<Integer, Double> turtleYMap,
                        Map<Integer, Double> turtleAngleMap,
                        Map<Integer, Boolean> turtleVisibilityMap,
                        Collection<Path> paths,
                        Color pen,
                        Color bg,
                        Boolean gridStatus,
                        Boolean turtleStatus,
                        Integer penSize) {
        adjustTurtle(activeTurtleList, turtleXMap, turtleYMap, turtleAngleMap, turtleVisibilityMap,
                     paths);
        myGridStatus = gridStatus;
        myTurtleStatus = turtleStatus;
        myError = error;
        myPenSize = 1; // TODO: set to actual penSize;
        changeBackgroundColor(new JGColor(0, 0, 0));
        changePenColor(new JGColor(250, 0, 0));

    }

    public void setGridStatus (boolean b) {
        myGridStatus = b;

    }

    public void setTurtleStatus (boolean b) {
        myTurtleStatus = b;
    }
}
