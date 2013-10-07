package view.display;

import jgame.JGObject;
import jgame.platform.JGEngine;

public class TurtleSprite extends JGObject {

    public TurtleSprite (JGEngine engine, double x, double y, int CID) {
      super("turtle", true, x, y, CID, "turtleGif", 0, 0, 0, 0,-1);
    }

}
