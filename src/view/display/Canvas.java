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
        setCanvasSettings(Constants.X_TILES, Constants.Y_TILES, Constants.TILE_HEIGHT, Constants.TILE_HEIGHT, null , JGColor.blue, null);
        defineImage("turtleGif", "-", 0, "Cattest.gif", "-", 0, 0, 50, 50);
        //turtle = new TurtleSprite(this, Constants.CANVAS_WIDTH/2, Constants.CANVAS_HEIGHT/2, 1);

    }

    @Override
    public void initGame () {
        setFrameRate(60, 2);

    }

}
