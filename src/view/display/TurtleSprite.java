package view.display;

import view.Constants;
import jgame.JGObject;
import jgame.platform.JGEngine;


/**
 * Class that
 * 
 * @author susanzhang93
 * 
 */
public class TurtleSprite extends JGObject {
    private JGEngine myEngine;
    private Integer myID;

    /**
     * Constructor for TurtleSprite class
     * 
     * @param engine JGEngine in which TurtleSprite JGObject is created
     * @param x initial x position of TurtleSprite
     * @param y initial y position of TurtleSprite
     * @param CID Collision ID of TurtleSprite
     */
    public TurtleSprite (JGEngine engine, double x, double y, int CID, String imgsrc, int turtleID) {
        super("turtle", true, x, y, CID, imgsrc, 0, 0, 0, 0, -1);
        myEngine = engine;
        myID = turtleID;

    }

    public void toggleStatus (boolean active) {
        myEngine.dbgShowBoundingBox(active);

    }

    public int getID () {
        return myID;
    }

    public double getOffsetX () {
        return this.x + Constants.TURTLE_OFFSET;
    }

    public double getOffsetY () {
        return this.y + Constants.TURTLE_OFFSET;
    }

}
