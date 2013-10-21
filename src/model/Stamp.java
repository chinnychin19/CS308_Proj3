package model;

public class Stamp {
    private double myX, myY, myAngle;
    private int myShapeIndex;

    protected Stamp (double x, double y, double angle, int shape) {
        myX = x;
        myY = y;
        myAngle = angle;
        myShapeIndex = shape;
    }

    public int getX () {
        return (int) myX;
    }

    public int getY () {
        return (int) myY;
    }

    public int getAngle () {
        return (int) myAngle;
    }

    public int getShapeIndex () {
        return myShapeIndex;
    }
}
