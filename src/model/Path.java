package model;

import java.awt.Color;


/**
 * 
 * Path class that keeps track of a path created by a turtle. Keeps track of the coordinates of the
 * starting and end points of the path, the color of the path, and the width of the path
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class Path {
    private double x1, x2, y1, y2;
    private Color myColor;
    private int myPenSize;

    /**
     * Constructor for a path object. Initializes the coordinates of the starting and end points of
     * the path, the color of the path, and the width of the path in pixels
     * 
     * @param x1 The x coordinate of the starting point of the path
     * @param y1 The y coordinate of the starting point of the path
     * @param x2 The x coordinate of the end point of the path
     * @param y2 The y coordinate of the end point of the path
     * @param color The color of the path
     * @param penSize The width of the path in pixels
     */
    public Path (double x1, double y1, double x2, double y2, Color color, int penSize) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        myColor = color;
        myPenSize = penSize;
    }

    /**
     * @return The color of the path
     */
    public Color getColor () {
        return myColor;
    }

    /**
     * @return The x coordinate of the starting point of the path
     */
    public double getX1 () {
        return x1;
    }

    /**
     * @return The x coordinate of the end point of the path
     */
    public double getX2 () {
        return x2;
    }

    /**
     * @return The y coordinate of the starting point of the path
     */
    public double getY1 () {
        return y1;
    }

    /**
     * @return The y coordinate of the end point of the path
     */
    public double getY2 () {
        return y2;
    }

    /**
     * @return The width of the path in pixels
     */
    public int getPenSize () {
        return myPenSize;
    }

    /**
     * 
     */
    // TODO: Javadoc for clone
    public Path clone () {
        Path newPath = new Path(x1, y1, x2, y2, myColor, myPenSize);
        return newPath;
    }
}
