package model.turtleMove;

import model.Turtle;


public class TurtleRelativeRotate extends TurtleMove {

    private double myDeltaAngle;

    public TurtleRelativeRotate (double deltaAngle) {
        myDeltaAngle = deltaAngle;
    }

    @Override
    public void doMove (Turtle thisTurtle) {
        thisTurtle.rotate(myDeltaAngle);
    }

}
