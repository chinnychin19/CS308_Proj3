package model;

public class Turtle {
    // TODO

    private double myX, myY, myAngle;
    private boolean fDrawing, fVisible;

    protected void Turtle () {
        // TODO
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

    public boolean isDrawing () {
        return fDrawing;
    }

    public boolean isVisible () {
        return fVisible;
    }

    protected void setDrawing (boolean drawing) {
        fDrawing = drawing;
    }

    protected void setVisible (boolean visible) {
        fVisible = visible;
    }

    protected void moveForward (double pixels) {
        // TODO
    }

    protected void rotate (double angle) {
        myAngle += angle;
    }

    protected void setAngle (double angle) {
        myAngle = angle;
    }

    protected void setLocation (double x, double y) {
        myX = x;
        myY = y;
    }
}
