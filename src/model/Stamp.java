package model;

public class Stamp {
    private double myX, myY, myAngle;
    private String myShape;

    protected Stamp (double x, double y, double angle, String shape) {
        myX = x;
        myY = y;
        myAngle = angle;
        myShape = shape;
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

    public String getShape () {
        return myShape;
    }
}
