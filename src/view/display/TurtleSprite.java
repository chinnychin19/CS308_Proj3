package view.display;

import jgame.JGObject;
import jgame.platform.JGEngine;


public class TurtleSprite extends JGObject {

    public TurtleSprite (JGEngine engine, double x, double y, int CID) {
      super("turtle", true, x, y, CID, "turtleGif", 0, 0, 0, 0,-1);
    }

    /**
     * Moves the turtle, draws line between old and new turtle location
     * 
     * @param x new x location of turtle
     * @param y new y location of turtle
     */
    protected void moveSprite(double x, double y){
        this.setPos(x, y);
        
    }
    
    
}
