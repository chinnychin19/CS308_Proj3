package view.display;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Model;
import model.Path;
import view.Constants;
import view.Observer;
import view.modulePanel.ModuleData;
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
public class Canvas extends JGEngine implements Observer {
    private String myImageName = "Turtle1.gif";
    private String myError = "";
    private Collection<Path> myPointList = new ArrayList<Path>();
    private JGColor myPenColor = JGColor.red;
    private boolean myGridOn = false;
    private boolean myStatusOn = true;
    private boolean myMouseClicked = false;

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
        defineImage(image + 1, "-", Constants.TURTLE_CID, myImageName, "-", 0, 0, 50, 50);

        TurtleSprite myTurtle = new TurtleSprite(this, Constants.CANVAS_WIDTH / 2 -
                                                       Constants.TURTLE_OFFSET,
                                                 Constants.CANVAS_HEIGHT / 2 -
                                                         Constants.TURTLE_OFFSET, 1,
                                                 image + 1);
        myTurtleMap.put(1, myTurtle);
        myActiveTurtleIDs.add(1);
    }

    @Override
    public void doFrame () {
        handleMouseClick();
    }

    /**
     * Sends coordinates of mouse clicking
     */
    public void handleMouseClick () {
        if (getMouseButton(1)) {
            myMouseClicked = true;
            System.out.println(getMouseX() + " " + getMouseY()); // How to ensure only once?
        }

        else if (!getMouseButton(1) && myMouseClicked) {
            myMouseClicked = false;
        }
    }

    @Override
    public void paintFrame () {
        super.paintFrame();

        if (myGridOn) {
            drawGrid();
        }

        if (myStatusOn) {
            drawStatus();
        }

        displayError(myError);

        if (getKey('S')) {
            Stamp();
        }

        if (getKey('C')) {
            clearStamps();
        }

        drawPath();
    }

    public void drawStatus () {
        int offset = 0;

        for (int ID : myActiveTurtleIDs) {

            TurtleSprite currentTurtle = getTurtle(ID);

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

    public TurtleSprite getTurtle (int ID) {
        if (myTurtleMap.containsKey(ID)) {
            return myTurtleMap.get(ID);
        }

        else {
            defineImage(image + ID, "-", Constants.TURTLE_CID, myImageName, "-", 0, 0, 50, 50);

            TurtleSprite myTurtle = new TurtleSprite(this, Constants.CANVAS_WIDTH / 2 -
                                                           Constants.TURTLE_OFFSET,
                                                     Constants.CANVAS_HEIGHT / 2 -
                                                             Constants.TURTLE_OFFSET, 1,
                                                     "turtleGif");
            myTurtleMap.put(ID, myTurtle);
            return myTurtle;
        }
    }

    public void changeTurtleVisiblity (int ID, boolean visible) {
        TurtleSprite tempTurtle = myTurtleMap.get(ID);

        if (visible != tempTurtle.getVisible()) {
            if (visible) {
                tempTurtle.resume();
            }

            else {
                tempTurtle.suspend();
            }
        }
        tempTurtle.setVisible(visible);
    }

    /**
     * Method that draws the turtle's path from Model's stored list of paths
     */
    public void drawPath () {
        for (int i = 0; i < myPointList.size(); i++) {
            Path toDraw = ((ArrayList<Path>) myPointList).get(i);
            // splitLine(toDraw);
            drawLine(toDraw.getX1() + Constants.CANVAS_WIDTH / 2, -toDraw.getY1() +
                                                                  Constants.CANVAS_WIDTH / 2,
                     toDraw.getX2() + Constants.CANVAS_WIDTH / 2, -toDraw.getY2() +
                                                                  Constants.CANVAS_WIDTH / 2, 1,
                     myPenColor);

        }
    }

    /**
     * Method to display error
     * 
     * @param error
     */
    public void displayError (String error) {
        drawString(error, Constants.CANVAS_WIDTH / 2, Constants.CANVAS_HEIGHT *
                                                      Constants.ERROR_MESSAGE_OFFSET, 0,
                   new JGFont("arial", 0, 12), JGColor.red);
    }

    public void setError (String error) {
        myError = error;
    }

    /**
     * Method that changes turtle image
     * 
     * @param imageName name of image
     */
    public void changeTurtleImage (String imageName) {

        myImageName = imageName;
        defineImage("turtleGif", "-", Constants.TURTLE_CID, myImageName, "-", 0, 0, 50, 50);

        for (int ID : myActiveTurtleIDs) {
            adjustImageAngle(ID, getTurtle(ID).getHeading());
        }

    }

    public void moveTurtle (int ID, double x, double y) {
        TurtleSprite toMove = getTurtle(ID);

        toMove.setPos(forceWithinBounds(x) + Constants.CANVAS_WIDTH / 2 - Constants.TURTLE_OFFSET,
                      -forceWithinBounds(y) + Constants.CANVAS_HEIGHT / 2 - Constants.TURTLE_OFFSET);

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
    public void changeBackgroundColor (JGColor color) {
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
     * Method that toggles grid on/off
     */
    public void toggleGrid () {
        myGridOn = !myGridOn;
    }

    /**
     * Method that toggles turtle status on/off
     */
    public void toggleStatus () {
        myStatusOn = !myStatusOn;
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

    public void clearStamps () {
        removeObjects(null, Constants.STAMP_CID);
        stampCounter = 0;
    }

    public void Stamp () {
        for (int ID : myTurtleMap.keySet()) {
            TurtleSprite tempTurtle = myTurtleMap.get(ID);
            String temp = "test" + stampCounter;
            defineImage(temp, "-", Constants.TURTLE_CID, myImageName, "-", 0, 0, 50, 50);
            new TurtleSprite(this, tempTurtle.x, tempTurtle.y, Constants.STAMP_CID,
                             temp);
        }

        stampCounter++;
    }

    public void adjustImageAngle (int ID, double angle) { // TODO Make this cleaner/work

        if (angle >= 45 && angle < 135) {
            myImageName = myImageName.substring(0, 7) + ".gif";
        }

        else if (angle >= 135 && angle < 225) {
            myImageName = myImageName.substring(0, 7) + "_2.gif";
        }

        else if (angle >= 225 && angle < 315) {
            myImageName = myImageName.substring(0, 7) + "_3.gif";
        }

        else if (angle >= 315 || angle < 45) {
            myImageName = myImageName.substring(0, 7) + "_4.gif";
        }

        defineImage("turtleGif" + ID, "-", Constants.TURTLE_CID, myImageName, "-", 0, 0, 50,
                    50);
    }

    @Override
    public void update (String error,
                        String updateVariable,
                        Map<String, Collection<ModuleData>> moduleMap,
                        ArrayList<Integer> activeTurtleList,
                        Map<Integer, Double> turtleXMap,
                        Map<Integer, Double> turtleYMap,
                        Map<Integer, Double> turtleAngleMap,
                        Map<Integer, Boolean> turtleVisibilityMap,
                        Collection<Path> paths, Color bgColor) {
        adjustTurtle(activeTurtleList, turtleXMap, turtleYMap, turtleAngleMap, turtleVisibilityMap,
                     paths);
        setError(error);
    }

    private void adjustTurtle (ArrayList<Integer> activeTurtleList,
                               Map<Integer, Double> turtleXMap,
                               Map<Integer, Double> turtleYMap,
                               Map<Integer, Double> turtleAngleMap,
                               Map<Integer, Boolean> turtleVisibilityMap,
                               Collection<Path> paths) {
        setActiveTurtles(activeTurtleList);

        for (Integer ID : activeTurtleList) {
            moveTurtle(ID, turtleXMap.get(ID), turtleYMap.get(ID));
            setHeading(ID, turtleAngleMap.get(ID));
            changeTurtleVisiblity(ID, turtleVisibilityMap.get(ID));
        }

        setPaths(paths);
    }
}
