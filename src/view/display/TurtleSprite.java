package view.display;

import jgame.JGObject;
import jgame.platform.JGEngine;


/**
 * Class that
 * 
 * @author susanzhang93
 * 
 */
public class TurtleSprite extends JGObject {

    /**
     * Constructor for TurtleSprite class
     * 
     * @param engine JGEngine in which TurtleSprite JGObject is created
     * @param x initial x position of TurtleSprite
     * @param y initial y position of TurtleSprite
     * @param CID Collision ID of TurtleSprite
     */
    public TurtleSprite (JGEngine engine, double x, double y, int CID) {
        super("turtle", true, x, y, CID, "turtleGif", 0, 0, 0, 0, -1);
    }

    // Temporarily here for testing
    public TurtleSprite (double x, double y, int CID) {
        super("turtle", true, x, y, CID, "turtleGif", 0, 0, 0, 0, -1);
    }

}
