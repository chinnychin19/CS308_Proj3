package model;

public class Turtle {
    // TODO

    private double myX, myY, myAngle;
    private boolean fDrawing, fVisible;

    protected Turtle () {
        myX = 0;
        myY = 0;
        myAngle = Math.PI / 2; //only internally stored in radians
    }
    
    public void forward(double pixels) {
        myX += Math.cos(myAngle) * pixels;
        myY += Math.sin(myAngle) * pixels;
    }

    protected int getX () {
        return (int) myX;
    }

    protected int getY () {
        return (int) myY;
    }

    protected int getAngle () {
        return (int) (myAngle/Math.PI*180); //internally stored in radians
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
