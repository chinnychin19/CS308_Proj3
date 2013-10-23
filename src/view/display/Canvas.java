package view.display;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import model.Model;
import model.Path;
import view.Constants;
import view.Updatable;
import view.ViewController;
import jgame.JGColor;
import jgame.JGFont;
import jgame.JGPoint;
import jgame.platform.JGEngine;
import model.Stamp;


/**
 * Class that displays the turtle sprite(s) within the canvas.
 * 
 * 
 */
public class Canvas extends JGEngine implements Updatable {
    private ViewController myController;
    private String myImageName = "Turtle1_1.gif";
    private String myError = "";
    private Collection<Path> myPointList = new ArrayList<Path>();
    private JGColor myPenColor = JGColor.red;
    private boolean myGridStatus = false;
    private boolean myTurtleStatus = true;
    private boolean myMouseClicked = false;
    private boolean myHighlights = false;

    private Map<Integer, TurtleSprite> myTurtleMap = new HashMap<Integer, TurtleSprite>();
    private ArrayList<Integer> myActiveTurtleIDs = new ArrayList<Integer>();
    private Collection<Stamp> myTurtleStamps = new ArrayList<Stamp>();
    private String image = "turtleGif";
    private Model myCurrentModel;

    public static void main (String[] args) {
        new Canvas(new JGPoint(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT));
    }

    public Canvas (Model model) {
        initEngineComponent(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
        myCurrentModel = model;
    }

    public void addController (ViewController controller) {
        myController = controller;
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

        displayError(myError);
        
        if (myHighlights) {
            for (int ID : myActiveTurtleIDs) {
                if (!myTurtleMap.get(ID).isSuspended()) {
                    highlightTurtle(ID);
                }
            }
        }

        drawPath();
    }

    private void drawStatus () {
        int offset = 0;

        for (int ID : myActiveTurtleIDs) {
            if (!myTurtleMap.containsKey(ID)) {
                addNewTurtle(ID);
            }

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

        }

        else if (!getMouseButton(1) && myMouseClicked) {
            myMouseClicked = false;
            System.out.println((getMouseX() - Constants.CANVAS_WIDTH / 2) + " " + (-getMouseY() +
                               Constants.CANVAS_HEIGHT / 2)); // TODO: How to ensure only once?
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
                     toDraw.getPenSize(),
                     colorToJGColor(toDraw.getColor()));

        }
    }

    private JGColor colorToJGColor (Color c) {
        return new JGColor(c.getRed(), c.getGreen(), c.getBlue());
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
    public void changeTurtleImage (int ID, String imageName) { 
            String newName=adjustImageAngle(imageName, myTurtleMap.get(ID).getHeading());
            defineImage(image + ID, newName);
 

    }

    /**
     * Sets heading of turtle
     */
    public void setHeading (int ID, double newHeading, String imageName) {
        if (myTurtleMap.get(ID).getHeading() != newHeading) {
            myTurtleMap.get(ID).setHeading(newHeading);
            String newName = adjustImageAngle(imageName, newHeading);
            defineImage(image + ID, newName);
        }

    }
    
    private String adjustImageAngle (String imageName, double angle) {
        int index;
        if (angle < 45) {
            angle = 360 + angle;
        }

        index = (int) Math.floor((angle - 45) / 90 + 1);

        return imageName.substring(0, 7) + "_" + index + ".gif";
    }

    private void defineImage (String name, String imageName) {
        defineImage(name, "-", Constants.TURTLE_CID, imageName, "-", 0, 0, 50,
                    50);
    }


    private void moveTurtle (int ID, double x, double y) {
        TurtleSprite toMove = myTurtleMap.get(ID);

        toMove.setPos(forceWithinBounds(x) + Constants.CANVAS_WIDTH / 2 -
                      Constants.TURTLE_OFFSET,
                      -forceWithinBounds(y) + Constants.CANVAS_HEIGHT / 2 - Constants.TURTLE_OFFSET);

        // toMove.setPos(x + Constants.CANVAS_WIDTH / 2 - Constants.TURTLE_OFFSET,
        // -y + Constants.CANVAS_HEIGHT / 2 - Constants.TURTLE_OFFSET);

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

    private void drawStamps () {
        for (int i = 0; i < myTurtleStamps.size(); i++) {
            Stamp s = ((ArrayList<Stamp>) myTurtleStamps).get(i);
            String newName = adjustImageAngle(s.getShape(), s.getAngle());
            defineImage("" + i, "-", Constants.TURTLE_CID, newName, "-", 0, 0, 50, 50);
            drawImage("" + i, s.getX() + Constants.CANVAS_WIDTH / 2 - Constants.TURTLE_OFFSET,
                      -s.getY() + Constants.CANVAS_HEIGHT / 2 - Constants.TURTLE_OFFSET);
        }
    }

    private void highlightTurtle (int ID) {
        double x = myTurtleMap.get(ID).x;
        double y = myTurtleMap.get(ID).y;
        drawLine(x, y, x + 50, y, 1, JGColor.green);
        drawLine(x + 50, y, x + 50, y + 50, 1, JGColor.green);
        drawLine(x + 50, y + 50, x, y + 50, 1, JGColor.green);
        drawLine(x, y + 50, x, y, 1, JGColor.green);
    }

 
    private void addNewTurtle (int ID) {
        defineImage(image + ID, "-", Constants.TURTLE_CID, myImageName, "-", 0, 0, 50, 50);

        TurtleSprite myTurtle =
                new TurtleSprite(this, Constants.CANVAS_WIDTH / 2 - Constants.TURTLE_OFFSET,
                                 Constants.CANVAS_HEIGHT / 2 -
                                         Constants.TURTLE_OFFSET, 1,
                                 image + ID);
        myTurtleMap.put(ID, myTurtle);
    }


    public void setGridStatus (boolean b) {
        myGridStatus = b;

    }

    public void setTurtleStatus (boolean b) {
        myTurtleStatus = b;
    }

    public void setHighlights (boolean b) {
        myHighlights = b;
    }

    @Override
    public void update (String error) {
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
        myImageName = myCurrentModel.getShape();
        myTurtleStamps = stamps;     
        myError = error;
        myActiveTurtleIDs = activeTurtleList;
        
        changeBackgroundColor(colorToJGColor(bg));
        changePenColor(colorToJGColor(pen));
        
        for (Integer ID : activeTurtleList) {
            if (!myTurtleMap.containsKey(ID)) {
                addNewTurtle(ID);
            }
            
            changeTurtleImage(ID, turtleShapeList.get(ID));
            setHeading(ID, turtleAngleMap.get(ID), turtleShapeList.get(ID));
            moveTurtle(ID, turtleXMap.get(ID), turtleYMap.get(ID));
            changeTurtleVisiblity(ID, turtleVisibilityMap.get(ID));
        }

        myTurtleStamps = stamps;
        setPaths(paths);

    }

    @Override
    public void changeModel (Model model) {
        myCurrentModel = model;

    }

    private ArrayList<Integer> getActiveTurtles () {
        return (ArrayList<Integer>) myCurrentModel.getActiveTurtleIDs();
    }

    private Map<Integer, Double> getTurtleX () {
        Map<Integer, Double> turtleXMap = new HashMap<Integer, Double>();
        ArrayList<Integer> activeTurtleList = getActiveTurtles();
        for (Integer ID : activeTurtleList) {
            turtleXMap.put(ID, myCurrentModel.getTurtleX(ID));
        }

        return turtleXMap;

    }

    private Map<Integer, Double> getTurtleY () {
        Map<Integer, Double> turtleYMap = new HashMap<Integer, Double>();
        ArrayList<Integer> activeTurtleList = getActiveTurtles();
        for (Integer ID : activeTurtleList) {
            turtleYMap.put(ID, myCurrentModel.getTurtleY(ID));
        }

        return turtleYMap;

    }

    private Map<Integer, Double> getTurtleAngle () {
        Map<Integer, Double> turtleAngleMap = new HashMap<Integer, Double>();
        ArrayList<Integer> activeTurtleList = getActiveTurtles();
        for (Integer ID : activeTurtleList) {
            turtleAngleMap.put(ID, myCurrentModel.getTurtleAngle(ID));
        }

        return turtleAngleMap;

    }

    private Map<Integer, Boolean> getTurtleVisibility () {
        Map<Integer, Boolean> turtleVisibilityMap = new HashMap<Integer, Boolean>();
        ArrayList<Integer> activeTurtleList = getActiveTurtles();
        for (Integer ID : activeTurtleList) {
            turtleVisibilityMap.put(ID, myCurrentModel.isTurtleVisible(ID));
        }

        return turtleVisibilityMap;

    }

    private Map<Integer, String> getTurtleShape () {
        ArrayList<Integer> activeTurtleList = getActiveTurtles();
        Map<Integer, String> activeTurtleShapes = new HashMap<Integer, String>();
        for (Integer ID : activeTurtleList) {
            activeTurtleShapes.put(ID, myCurrentModel.getTurtleShape(ID));
        }
        return activeTurtleShapes;
    }
}