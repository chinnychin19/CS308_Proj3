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
 * Class that displays the turtle sprite within the canvas. Grid can be turned on or off
 * 
 * @author susanzhang93
 * 
 */
public class Canvas extends JGEngine {
    private TurtleSprite turtle = null;
    private boolean gridOn = false;
    private boolean statusOn = true;
    private boolean visible = true;
    private String imageName = "Turtle1.gif";
    private JGColor penColor = JGColor.red;
    private double heading = 90;
    private Collection<Path> pointList = new ArrayList<Path>();
    private String error = "";

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
        defineImage("turtleGif", "-", Constants.TURTLE_CID, imageName, "-", 0, 0, 50, 50);
        // turtleGif is name of image within JGEngine, imageName is the actual name of image

        // TODO: Deal with image offset - TURTLE_OFFSET
        turtle =
                new TurtleSprite(this, Constants.CANVAS_WIDTH / 2 - Constants.TURTLE_OFFSET,
                                 Constants.CANVAS_HEIGHT / 2 - Constants.TURTLE_OFFSET, 1,
                                 "turtleGif");
        // dbgShowBoundingBox (true);
    }

    @Override
    public void paintFrame () {
        super.paintFrame();

        if (gridOn) {
            drawGrid();
        }

        if (statusOn) {
            drawStatus();
        }

        displayError(error);

        drawPath();
        // TODO: Move turtle to end location (get from Model)

    }

    public void drawStatus () {
        int offset = 5;
        // TODO: Get data from Model
        drawString("X: " + (turtle.getOffsetX() - Constants.CANVAS_WIDTH / 2), 5, offset, -1,
                   new JGFont("arial", 0, 12),
                   penColor);
        drawString("Y: " + (-turtle.getOffsetY() + Constants.CANVAS_HEIGHT / 2), 5, offset += 13,
                   -1,
                   new JGFont("arial", 0, 12),
                   penColor);
        drawString("Heading: " + heading, 5, offset += 13, -1, new JGFont("arial", 0, 12),
                   penColor);
    }

    public void isTurtleVisible (boolean visible) {
        if (visible != this.visible) {
            if (visible) {
                turtle.resume();
            }

            else {
                turtle.suspend();
            }
        }

        this.visible = visible;
    }

    /**
     * Method that draws the turtle's path from Model's stored list of paths
     */
    public void drawPath () {
        for (int i = 0; i < pointList.size(); i++) {
            Path toDraw = ((ArrayList<Path>) pointList).get(i);
            drawLine(toDraw.getX1() + Constants.CANVAS_WIDTH / 2, -toDraw.getY1() +
                                                                  Constants.CANVAS_WIDTH / 2,
                     toDraw.getX2() + Constants.CANVAS_WIDTH / 2, -toDraw.getY2() +
                                                                  Constants.CANVAS_WIDTH / 2, 1,
                     penColor);
        }
    }

    /**
     * Method to display error
     * 
     * @param error
     */
    public void displayError (String error) {
        drawString(error, Constants.CANVAS_WIDTH / 2, Constants.CANVAS_HEIGHT * .95, 0,
                   new JGFont("arial", 0, 12), JGColor.red);
    }

    public void setError (String error) {
        this.error = error;
    }

    /**
     * Method that changes turtle image
     * 
     * @param imageName name of image
     */
    public void changeTurtleImage (String imageName) {
        this.imageName = imageName;
        defineImage("turtleGif", "-", Constants.TURTLE_CID, this.imageName, "-", 0, 0, 50, 50);
        adjustImageAngle(this.heading);
    }

    /**
     * Moves turtle sprite, and draws line between displacement
     * 
     * @param x new x location of turtle
     * @param y new y location of turtle
     */
    public void moveTurtle (double x, double y) {
        turtle.setPos(x + Constants.CANVAS_WIDTH / 2 - Constants.TURTLE_OFFSET,
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
        penColor = color;
    }

    /**
     * Method that toggles grid on/off
     */
    public void toggleGrid () {
        gridOn = !gridOn;
    }

    /**
     * Method that toggles turtle status on/off
     */
    public void toggleStatus () {
        statusOn = !statusOn;
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
        pointList = list;
    }

    /**
     * Sets heading of turtle
     */
    public void setHeading (double newHeading) {

        if (this.heading != newHeading) {

            adjustImageAngle(newHeading);

        }

        this.heading = newHeading;

    }

    public void adjustImageAngle (double heading) {
        if (heading >= 45 && heading < 135) {
            imageName = imageName.substring(0, 7) + ".gif";
        }

        else if (heading >= 135 && heading < 225) {
            imageName = imageName.substring(0, 7) + "_2.gif";
        }

        else if (heading >= 225 && heading < 315) {
            imageName = imageName.substring(0, 7) + "_3.gif";
        }

        else if (heading >= 315 || heading < 45) {
            imageName = imageName.substring(0, 7) + "_4.gif";
        }

        defineImage("turtleGif", "-", Constants.TURTLE_CID, imageName, "-", 0, 0, 50,
                    50);
    }

    /**
     * Moves turtle sprite to center of canvas (is this completely necessary)
     */
    public void moveToOrigin () {
        turtle.setPos(Constants.CANVAS_WIDTH / 2,
                      Constants.CANVAS_HEIGHT / 2);
    }

    public String getImageName () {
        return imageName;
    }

}
