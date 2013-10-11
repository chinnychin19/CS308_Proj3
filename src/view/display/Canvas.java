package view.display;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import org.jbox2d.common.Vec2;
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
    private boolean penUp = false;
    private String gifName = "Turtle1.gif";
    private JGColor penColor = JGColor.red;

    private double heading = 0;
    private ArrayList<Point2D.Double> pointList = new ArrayList<Point2D.Double>();

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
        defineImage("turtleGif", "-", Constants.TURTLE_CID, gifName, "-", 0, 0, 50, 50);

        // TODO: Deal with image offset - TURTLE_OFFSET
        turtle = new TurtleSprite(this, Constants.CANVAS_WIDTH / 2, Constants.CANVAS_HEIGHT / 2, 1);

        pointList.add(new Double(Constants.CANVAS_WIDTH / 2, Constants.CANVAS_HEIGHT / 2));
        pointList.add(new Double(10, 500));
        pointList.add(new Double(80, 80));

    }

    @Override
    public void paintFrame () {
        super.paintFrame();

        if (gridOn && !penUp) {
            drawGrid();
        }

        if (statusOn) {
            drawStatus();
        }

        drawPath();
        // TODO: Move turtle to end location (get from Model)

    }

    public void drawStatus () {
        int offset = 5;
        // TODO: Get data from Model
        drawString("X: " + turtle.x, 5, offset, -1, new JGFont("arial", 0, 12),
                   penColor);
        drawString("Y: " + turtle.y, 5, offset += 13, -1, new JGFont("arial", 0, 12),
                   penColor);
        drawString("Heading: ", 5, offset += 13, -1, new JGFont("arial", 0, 12),
                   penColor);
    }

    /**
     * Method that draws the turtle's path from Model's stored list of paths
     */
    public void drawPath () {
        for (int i = 0; i < pointList.size() - 1; i++) {
            Point2D start = pointList.get(i);
            Point2D end = pointList.get(i + 1);
            drawLine(start.getX(), start.getY(), end.getX(), end.getY(), 2, penColor);
        }
    }

    /**
     * Method to convert JGame coordinates to SLogo defined coordinates
     * 
     * @param coordinate coordinate in form of Point2D
     */
    public Point2D convertCoordinates (Point2D coordinate) {
        coordinate.setLocation(coordinate.getX() - Constants.GUI_WIDTH, coordinate.getY() -
                                                                        Constants.GUI_HEIGHT);
        return coordinate;
    }

    /**
     * Method that changes turtle image
     * 
     * @param imageName name of image
     */
    public void changeTurtleImage (String imageName) {
        defineImage("turtleGif", "-", Constants.TURTLE_CID, imageName, "-", 0, 0, 50, 50);
    }

    /**
     * Method that checks to see if new turtle coordinates are within the bounds of the canvas and
     * fixes them accordingly if so
     * 
     * @param x x position
     * @param y y position
     * @return Vector containing modular x and y coordinates
     */
    public Vec2 forceWithinBounds (double x, double y) {
        if (x > Constants.CANVAS_WIDTH) {
            x = x % Constants.CANVAS_WIDTH;
        }

        else if (x < 0) {
            x = Constants.CANVAS_WIDTH - (Math.abs(x) % Constants.CANVAS_WIDTH);
        }

        if (y > Constants.CANVAS_HEIGHT) {
            y = y % Constants.CANVAS_HEIGHT;
        }

        else if (y < 0) {
            y = Constants.CANVAS_HEIGHT - (Math.abs(y) % Constants.CANVAS_HEIGHT);
        }

        return new Vec2((float) x, (float) y);
    }

    /**
     * Moves turtle sprite, and draws line between displacement
     * 
     * @param x new x location of turtle
     * @param y new y location of turtle
     */
    public void moveTurtle (double x, double y) {
        turtle.setPos(x, y);
    }

    public void moveTurtle (Point2D.Double coordinate) {
        turtle.setPos(coordinate.getX(), coordinate.getY());
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
    public void setPoints (ArrayList<Point2D.Double> list) {
        pointList = list;
    }

    /**
     * Sets heading of turtle
     */
    public void setHeading (double newHeading) {
        this.heading = newHeading;
    }

    /**
     * Moves turtle sprite to center of canvas (is this completely necessary)
     */
    public void moveToOrigin () {
        turtle.setPos(Constants.CANVAS_WIDTH / 2,
                      Constants.CANVAS_HEIGHT / 2);
    }

}
