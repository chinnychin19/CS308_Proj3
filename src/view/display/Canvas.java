package view.display;

import view.Constants;
import jgame.JGColor;
import jgame.platform.JGEngine;



public class Canvas extends JGEngine {
    TurtleSprite turtle = null;
    
    public Canvas () {
        initEngineComponent(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
    }

    @Override
    public void initCanvas () {
        setCanvasSettings(Constants.X_TILES, Constants.Y_TILES, Constants.TILE_HEIGHT, Constants.TILE_HEIGHT, null , Constants.CANVAS_COLOR, null);
    }

    @Override
    public void initGame () {
        setFrameRate(60, 2);
        defineImage("turtleGif", "-", 0, "Turtle1.gif", "-", 0, 0, 50, 50);
        //Why these x and y?
        turtle = new TurtleSprite(this, Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT, 1);
        drawGrid();
    }
    
    /**
     * Method that draws grid
     */
    public void drawGrid(){
       drawLine(50, 10, 500, 50);
    }

}
