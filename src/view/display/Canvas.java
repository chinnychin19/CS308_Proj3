package view.display;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import org.jbox2d.common.Vec2;
import view.Constants;
import jgame.JGColor;
import jgame.JGImage;
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
    private String gifName = "Turtle1.gif";
    private JGColor penColor = JGColor.red;
    private ArrayList<Point2D.Double> pointList = new ArrayList<Point2D.Double>();

    
    // TODO: Deal with JGame coordinates vs SLogo defined coordinates :(

    public static void main (String[] args) {
        new Canvas(new JGPoint(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT));
    }

     public Canvas () {
     initEngineComponent(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
     }

    public Canvas (JGPoint size) {
        initEngine(size.x, size.y);

    }

//    public Canvas () {
//        initEngineApplet();
//    }

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
        
        pointList.add(new Double(0.0,0.1));
        pointList.add(new Double(10,500));
        pointList.add(new Double(80,80));
        
        
    }

    @Override
    public void paintFrame () {
        super.paintFrame();

        if (gridOn) {
            drawGrid();
        }

        //drawLine(0, 0, 100, 100, 2, JGColor.red);
        //System.out.println(pointList);
         for (int i=0; i < pointList.size()-1; i++){
             Point2D start = pointList.get(i);
             Point2D end = pointList.get(i+1);
             drawLine(start.getX(), start.getY(), end.getX(), end.getY(), 2, penColor);
         }
        
        // TODO: Implement actual GUI toggling
        if (getKey('M')) {
            moveTurtle(0, -10);
            clearKey('M');
        }

    }
    
    /**
     * Method to convert JGame coordinates to SLogo defined coordinates 
     *
     * @param x
     */
    public void convertCoordinates(double x){
       
    }
    
    /**
     * Method that changes turtle image
     * @param gifName
     */
    public void changeTurtleImage(String gifName){
        defineImage("turtleGif", "-", Constants.TURTLE_CID, gifName, "-", 0, 0, 50, 50);
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
     * @param x change in x direction
     * @param y change in y direction
     */
    public void moveTurtle (double x, double y) {
        double newX = turtle.x + x;
        double newY = turtle.y + y;

        // Relative
        drawLine(turtle.x, turtle.y, newX, newY);
        turtle.setPos(newX, newY);
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
    
    public void changePenColor (JGColor color){
        penColor = color;
    }
    
    /**
     * Method that toggles grid on/off
     */
    public void toggleGrid(){
        gridOn = !gridOn;
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
     * Moves turtle sprite to center of canvas
     */
    public void moveToOrigin () {
        turtle.setPos(Constants.CANVAS_WIDTH / 2 - Constants.TURTLE_OFFSET,
                      Constants.CANVAS_HEIGHT / 2 - Constants.TURTLE_OFFSET);
    }

}
