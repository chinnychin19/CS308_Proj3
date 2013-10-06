package model.turtleMove;

import model.Turtle;


public class TurtleRelativeMove extends TurtleMove {

    private double myPixels;

    public TurtleRelativeMove (double pixels) {
        myPixels = pixels;
    }

    @Override
    public void doMove (Turtle thisTurtle) {
        double oldX = thisTurtle.getX(), oldY = thisTurtle.getY();
        thisTurtle.moveForward(myPixels);
        if (thisTurtle.isDrawing()) {
            thisTurtle.addPath(oldX, oldY, thisTurtle.getX(), thisTurtle.getY());
        }
    }

}
