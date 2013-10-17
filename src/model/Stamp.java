package model;

public class Stamp {
    private int myX, myY, myAngle, myShapeIndex;

    protected Stamp (int x, int y, int angle, int shape) {
        myX = x;
        myY = y;
        myAngle = angle;
        myShapeIndex = shape;
    }

    public int getX () {
        return myX;
    }

    public int getY () {
        return myY;
    }

    public int getAngle () {
        return myAngle;
    }

    public int getShapeIndex () {
        return myShapeIndex;
    }
}
