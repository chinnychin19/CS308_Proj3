package model;

public class Turtle {
    // TODO

    private double myX, myY, myAngle;
    private boolean fDrawing, fVisible;
    private Model myModel;

    protected Turtle (Model m) {
        myModel = m;
        myX = 0;
        myY = 0;
        myAngle = 90;
    }

    protected int getX () {
        return (int) myX;
    }

    protected int getY () {
        return (int) myY;
    }

    protected int getAngle () {
        return (int) myAngle;
    }

    protected boolean isDrawing () {
        return fDrawing;
    }

    protected boolean isVisible () {
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
