package model;

import java.util.ArrayList;
import java.util.Collection;


public class Turtle {
    private double myX, myY, myAngle;
    private boolean fDrawing, fVisible;
    private Collection<Path> myPaths;

    protected Turtle () {
        myX = 0;
        myY = 0;
        fDrawing = true;
        fVisible = true;
        myAngle = Math.PI / 2; // only internally stored in radians
        myPaths = new ArrayList<Path>();
    }

    private void addPath (double x1, double y1, double x2, double y2) {
        myPaths.add(new Path(x1, y1, x2, y2));
    }

    public void clearPaths () {
        myPaths.clear();
    }

    public int getX () {
        return (int) myX;
    }

    public int getY () {
        return (int) myY;
    }

    public int getAngle () {
        return (int) radiansToDegrees(myAngle);
    }

    public boolean isDrawing () {
        return fDrawing;
    }

    public boolean isVisible () {
        return fVisible;
    }

    public void setDrawing (boolean drawing) {
        fDrawing = drawing;
    }

    public void setVisible (boolean visible) {
        fVisible = visible;
    }

    public double doRelativeMove (double pixels) {
        double oldX = myX, oldY = myY;
        myX += Math.cos(myAngle) * pixels;
        myY += Math.sin(myAngle) * pixels;
        if (isDrawing()) {
            addPath(oldX, oldY, myX, myY);
        }
        return pixels;
    }

    public double doAbsoluteMove (double x, double y) {
        double oldX = myX, oldY = myY;
        myX = x;
        myY = y;
        if (isDrawing()) {
            addPath(oldX, oldY, myX, myY);
        }
        return distance(oldX, oldY, x, y);
    }

    public double doRelativeRotate (double degrees) {
        double radians = degreesToRadians(degrees);
        myAngle = (myAngle + radians) % (2 * Math.PI); // maintains 0 to 2 PI
        return degrees;
    }

    public double doAbsoluteRotate (double degrees) {
        double deltaDegrees = degrees - radiansToDegrees(myAngle) % 360; // CCW change in degrees
        myAngle = degreesToRadians(degrees % 360); // maintains 0 to 2 PI
        return deltaDegrees;
    }

    public double doRotateTowards (double x, double y) {
        double dx = x - myX, dy = y - myY;
        double newRadians = Math.atan2(dy, dx); // (-Math.pi, Math.pi]
        double oldRadians = myAngle;
        newRadians = newRadians % (2 * Math.PI); // (0, 2 PI]
        myAngle = newRadians;  // maintains 0 to 2 PI
        double deltaRadians = (newRadians - oldRadians) % (2 * Math.PI);
        return deltaRadians;
    }

    private static double radiansToDegrees (double radians) {
        return radians / Math.PI * 180;
    }

    private static double degreesToRadians (double degrees) {
        return degrees / 180 * Math.PI;
    }

    private static double distance (double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
