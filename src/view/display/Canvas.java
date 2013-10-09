package view.display;

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
    public TurtleSprite turtle = null;

    public static void main (String[] args) {
        new Canvas(new JGPoint(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT));
    }

    // public Canvas () {
    // initEngineComponent(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
    // }

    public Canvas (JGPoint size) {
        initEngine(size.x, size.y);
    }

    public Canvas () {
        initEngineApplet();
    }

    @Override
    public void initCanvas () {
        setCanvasSettings(Constants.X_TILES, Constants.Y_TILES, Constants.TILE_HEIGHT,
                          Constants.TILE_HEIGHT, null, Constants.CANVAS_COLOR, null);
    }

    @Override
    public void initGame () {
        setFrameRate(Constants.FRAMES_PER_SECOND, 2);
        defineImage("turtleGif", "-", 0, "Turtle1.gif", "-", 0, 0, 50, 50);
        // TODO: Deal with image offset - TURTLE_OFFSET
        turtle = new TurtleSprite(this, Constants.CANVAS_WIDTH / 2, Constants.CANVAS_HEIGHT / 2, 1);
        paintFrame();

    }

    @Override
    public void paintFrame () {
        super.paintFrame();
        
        moveTurtle(100, 100);
        moveTurtle(100, 400);
        // TODO: Add boolean check for drawing grid
        drawGrid();
    }

    public Vec2 checkOnScreen (double x, double y) {
        if (x > Constants.CANVAS_WIDTH){
            x = x - Constants.CANVAS_WIDTH;
        }
        
        else if (x < 0){
            x = Constants.CANVAS_WIDTH - 1;
        }
        
        if (y > Constants.CANVAS_HEIGHT){
            y = y - Constants.CANVAS_HEIGHT;
        }
        
        else if (y < 0){
            y = Constants.CANVAS_HEIGHT;
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
        // //Absolute
        // drawLine(turtle.x, turtle.y, x, y, 10, JGColor.red);
        // turtle.setPos(x, y);

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
        setCanvasSettings(Constants.X_TILES, Constants.Y_TILES, Constants.TILE_HEIGHT,
                          Constants.TILE_HEIGHT, null, color, null);
    }

    /**
     * Moves turtle sprite to center of canvas
     */
    public void moveToOrigin () {
        turtle.setPos(Constants.CANVAS_WIDTH / 2 - Constants.TURTLE_OFFSET,
                      Constants.CANVAS_HEIGHT / 2 - Constants.TURTLE_OFFSET);
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

}
