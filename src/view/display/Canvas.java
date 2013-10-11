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
    private boolean penUp = false;
    private String gifName = "Turtle1.gif";
    private JGColor penColor = JGColor.red;
    private double heading = 90;
    private Collection<Path> pointList = new ArrayList<Path>();

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
        
        //moveTurtle(150, 150);
        //pointList.add(new Path(10,10, -300, 300));
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

        //displayError("test");

        drawPath();
        // TODO: Move turtle to end location (get from Model)

    }

    public void drawStatus () {
        int offset = 5;
        // TODO: Get data from Model
        drawString("X: " + (turtle.x - Constants.CANVAS_WIDTH / 2), 5, offset, -1,
                   new JGFont("arial", 0, 12),
                   penColor);
        drawString("Y: " + (-turtle.y + Constants.CANVAS_HEIGHT / 2), 5, offset += 13, -1,
                   new JGFont("arial", 0, 12),
                   penColor);
        drawString("Heading: " + heading, 5, offset += 13, -1, new JGFont("arial", 0, 12),
                   penColor);
    }

    /**
     * Method that draws the turtle's path from Model's stored list of paths
     */
    public void drawPath () {
        for (int i = 0; i < pointList.size(); i++) {
            Path toDraw = ((ArrayList<Path>) pointList).get(i);
            drawLine(toDraw.getX1()+Constants.CANVAS_WIDTH/2, -toDraw.getY1()+Constants.CANVAS_WIDTH/2, toDraw.getX2()+Constants.CANVAS_WIDTH/2, -toDraw.getY2()+Constants.CANVAS_WIDTH/2, 2, penColor);
        }
    }

    /**
     * Method to display error
     * 
     * @param error
     */
    public void displayError (String error) {
        drawString(error, Constants.CANVAS_WIDTH / 2, Constants.CANVAS_HEIGHT *.95, 0,
                   new JGFont("arial", 0, 12), JGColor.red);
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
     * Moves turtle sprite, and draws line between displacement
     * 
     * @param x new x location of turtle
     * @param y new y location of turtle
     */
    public void moveTurtle (double x, double y) {
        turtle.setPos(x + Constants.CANVAS_WIDTH / 2, -y + Constants.CANVAS_HEIGHT / 2);
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
