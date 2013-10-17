package view.display;

import java.util.ArrayList;
import java.util.Collection;
import model.Path;
import view.Constants;
import jgame.JGColor;
import jgame.JGFont;
import jgame.JGPoint;
import jgame.platform.JGEngine;


/**
 * Class that displays the turtle sprite(s) within the canvas. Grid can be turned on or off
 * 
 * @author susanzhang93
 * 
 */
public class Canvas extends JGEngine {
    private TurtleSprite myTurtle = null;
    private String myImageName = "Turtle1.gif";
    private String myError = "";
    private Collection<Path> myPointList = new ArrayList<Path>();
    private double myHeading = 90;
    private JGColor myPenColor = JGColor.red;
    private boolean myGridOn = false;
    private boolean myStatusOn = true;
    private boolean myVisible = true;
    private boolean myMouseClicked = false;
    private ArrayList<TurtleSprite> myTurtleList = new ArrayList<TurtleSprite>();
    private ArrayList<Integer> myActiveTurtleIDs = new ArrayList<Integer>();

    public static void main (String[] args) {
        new Canvas(new JGPoint(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT));
    }

    public Canvas () {
        initEngineComponent(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
    }

    public Canvas (JGPoint size) {
        initEngine(size.x, size.y);
    }

    // public Canvas () {
    // initEngineApplet();
    // }

    @Override
    public void initCanvas () {
        setCanvasSettings(Constants.X_TILES, Constants.Y_TILES, Constants.TILE_HEIGHT,
                          Constants.TILE_HEIGHT, null, Constants.CANVAS_COLOR, null);
    }

    @Override
    public void initGame () {
        setFrameRate(Constants.FRAMES_PER_SECOND, 2);
        defineImage("turtleGif", "-", Constants.TURTLE_CID, myImageName, "-", 0, 0, 50, 50);
        // turtleGif is name of image within JGEngine, imageName is the actual name of image

        myTurtle =
                new TurtleSprite(this, Constants.CANVAS_WIDTH / 2 - Constants.TURTLE_OFFSET,
                                 Constants.CANVAS_HEIGHT / 2 - Constants.TURTLE_OFFSET, 1,
                                 "turtleGif", 1);
    }
    
    @Override
    public void doFrame(){
        handleMouseClick();
    }
    
    /**
     * Sends coordinates of mouse clicking
     */
    public void handleMouseClick(){
        if (getMouseButton(1)){     
            myMouseClicked=true;
            System.out.println(getMouseX() + " " + getMouseY()); //How to ensure only once?
        }
        
        else if (!getMouseButton(1) && myMouseClicked){
            myMouseClicked=false;
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

        drawPath();
    }

    public void drawStatus () {
        int offset = 5;
        drawString("X: " + (myTurtle.getOffsetX() - Constants.CANVAS_WIDTH / 2), 5, offset, -1,
                   new JGFont("arial", 0, 12),
                   myPenColor);
        drawString("Y: " + (-myTurtle.getOffsetY() + Constants.CANVAS_HEIGHT / 2), 5, offset += 13,
                   -1,
                   new JGFont("arial", 0, 12),
                   myPenColor);
        drawString("Heading: " + myHeading, 5, offset += 13, -1, new JGFont("arial", 0, 12),
                   myPenColor);
    }

    public void isTurtleVisible (boolean visible) {
        if (visible != myVisible) {
            if (visible) {
                myTurtle.resume();
            }

            else {
                myTurtle.suspend();
            }
        }
        myVisible = visible;
    }

    /**
     * Method that draws the turtle's path from Model's stored list of paths
     */
    public void drawPath () {
        for (int i = 0; i < myPointList.size(); i++) {
            Path toDraw = ((ArrayList<Path>) myPointList).get(i);
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
        adjustImageAngle(myHeading);
    }

    /**
     * Moves turtle sprite, and draws line between displacement
     * 
     * @param x new x location of turtle
     * @param y new y location of turtle
     */
    public void moveTurtle (double x, double y) {
        myTurtle.setPos(x + Constants.CANVAS_WIDTH / 2 - Constants.TURTLE_OFFSET,
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
    public void setHeading (double newHeading) {

        if (myHeading != newHeading) {

            adjustImageAngle(newHeading);

        }

        myHeading = newHeading;

    }
    
    /**
     * 
     * @param pathPiece Part of path to be examined
     * @return altered path pieces (what?)
     */
//    public Path forceWithinBounds (Path pathPiece) {
//        if (pathPiece.getX2() > Constants.CANVAS_WIDTH) {
//            x = x % Constants.CANVAS_WIDTH;
//        }
//
//        else if (x.getX2() < 0) {
//            x = Constants.CANVAS_WIDTH - (Math.abs(x) % Constants.CANVAS_WIDTH);
//        }
//
//        if (y > Constants.CANVAS_HEIGHT) {
//            y = y % Constants.CANVAS_HEIGHT;
//        }
//
//        else if (y < 0) {
//            y = Constants.CANVAS_HEIGHT - (Math.abs(y) % Constants.CANVAS_HEIGHT);
//        }
//
//        return new Path((float) x, (float) y);
//    }
    

    public void adjustImageAngle (double angle) {
        if (angle >= 45 && angle < 135) {
            myImageName = myImageName.substring(0, 7);
        }

        else if (angle >= 135 && angle < 225) {
            myImageName = myImageName.substring(0, 7) + "_2";
        }

        else if (angle >= 225 && angle < 315) {
            myImageName = myImageName.substring(0, 7) + "_3";
        }

        else if (angle >= 315 || angle < 45) {
            myImageName = myImageName.substring(0, 7) + "_4";
        }

        defineImage("turtleGif", "-", Constants.TURTLE_CID, myImageName + ".gif", "-", 0, 0, 50,
                    50);
    }

}
