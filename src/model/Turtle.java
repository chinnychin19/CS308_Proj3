package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import model.turtleMove.*;


public class Turtle {
    private double myX, myY, myAngle;
    private boolean fDrawing, fVisible;
    private Queue<TurtleMove> myQueue;
    private Collection<Path> myPaths;

    protected Turtle () {
        myX = 0;
        myY = 0;
        fDrawing = true;
        fVisible = true;
        myAngle = Math.PI / 2; // only internally stored in radians
        myQueue = new LinkedList<TurtleMove>();
        myPaths = new ArrayList<Path>();
    }

    public void addPath (double x1, double y1, double x2, double y2) {
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

    protected int getAngle () {
        return (int) (myAngle / Math.PI * 180); // internally stored in radians
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

    public void moveForward (double pixels) {
        myX += Math.cos(myAngle) * pixels;
        myY += Math.sin(myAngle) * pixels;
    }

    public void rotate (double angle) {
        myAngle += angle; // Convert to radians
    }

    public void setAngle (double angle) {
        myAngle = angle; // Convert to radians
    }

    public void setLocation (double x, double y) {
        myX = x;
        myY = y;
    }

    public void addAbsoluteMove (double x, double y) {
        myQueue.add(new TurtleAbsoluteMove(x, y));
    }

    public void addRelativeMove (double pixels) {
        myQueue.add(new TurtleRelativeMove(pixels));
    }

    public void addAbsoluteRotate (double angle) {
        myQueue.add(new TurtleAbsoluteRotate(angle)); // in degrees
    }

    public void addRelativeRotate (double deltaAngle) {
        myQueue.add(new TurtleRelativeRotate(deltaAngle)); // in degrees
    }

    protected boolean hasNextMove () {
        return !myQueue.isEmpty();
    }

    protected void doNextMove () {
        myQueue.poll().doMove(this);
    }
}
