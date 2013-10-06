package model.turtleMove;

import model.Turtle;


public class TurtleAbsoluteMove extends TurtleMove {
    private double myX, myY;

    public TurtleAbsoluteMove (double x, double y) {
        myX = x;
        myY = y;
    }

    @Override
    public void doMove (Turtle thisTurtle) {
        if (thisTurtle.isDrawing()) {
            thisTurtle.addPath(thisTurtle.getX(), thisTurtle.getY(), myX, myY);
        }
        thisTurtle.setLocation(myX, myY);
    }

}