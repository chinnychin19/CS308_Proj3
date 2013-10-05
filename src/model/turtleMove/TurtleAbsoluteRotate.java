package model.turtleMove;

import model.Turtle;


public class TurtleAbsoluteRotate extends TurtleMove {

    private double myAngle;

    public TurtleAbsoluteRotate (double angle) {
        myAngle = angle;
    }

    @Override
    public void doMove (Turtle thisTurtle) {
        thisTurtle.setAngle(myAngle);

    }

}
