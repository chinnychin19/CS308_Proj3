package model.turtleMove;

import model.Turtle;


public class TurtleRelativeMove extends TurtleMove {

    private double myPixels;

    public TurtleRelativeMove (double pixels) {
        myPixels = pixels;
    }

    @Override
    public void doMove (Turtle thisTurtle) {
        thisTurtle.moveForward(myPixels);

    }

}
