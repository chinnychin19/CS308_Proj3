package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import model.instruction.error.ColorNotFound;


/**
 * 
 * Turtle class for creating and monitoring instances of turtles. Keeps track of a turtle's x and y
 * coordinates, its direction, whether or not its pen is down, whether or not it is visible, whether
 * or not it is active, a collection of its visible paths, a collection of its stamps, its ID, the
 * current instance of the model that it is being used by, the color and size of its pen, and the
 * index of the shape being used to portray it in the model
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */

public class Turtle {
    private double myX, myY, myAngle;
    private boolean isDrawing, isVisible, isActive;
    private Collection<Path> myPaths;
    private Collection<Stamp> myStamps;
    private int myID;
    private Model myModel;
    private Color myColor;
    private int myShapeIndex;
    private int myPenSize;

    /**
     * Constructor for a turtle object. Initializes a new turtle with the given ID in the given
     * model. Places the turtle at (0, 0), the middle of the screen, with an initial direction of 90
     * degrees, facing up. By default, a turtle will have its pen down, be visible, be active, and
     * have a shape index of 0. It initializes the turtle's pen color and size to the model's
     * current pen color and size. The constructor also creates new lists for the turtle's paths and
     * stamps. The turtle also stores the model it is being used by
     * 
     * @param id The ID of the turtle to be constructed
     * @param model The model instance that the turtle is being constructed in
     */
    protected Turtle (int id, Model model) {
        myID = id;
        myX = 0;
        myY = 0;
        isDrawing = true;
        isVisible = true;
        isActive = true;
        myAngle = Math.PI / 2; // only internally stored in radians
        myPaths = new ArrayList<Path>();
        myStamps = new ArrayList<Stamp>();
        myModel = model;
        myColor = model.getPenColor();
        myShapeIndex = 0; // by default
        myPenSize = model.getPenSize();
    }

    /**
     * @return The current shape index of the turtle
     */
    public int getShapeIndex () {
        return myShapeIndex;
    }

    /**
     * Sets the shape index of the turtle
     * 
     * @param shapeIndex The new shape index to set the turtle to
     */
    public void setShape (int shapeIndex) {
        myShapeIndex = shapeIndex;
    }

    /**
     * @return The current pen color of the turtle
     */
    public Color getColor () {
        return myColor;
    }

    /**
     * Sets the pen color of the turtle
     * 
     * @param color The new color to set the turtle's pen to
     */
    public void setColor (Color color) {
        myColor = color;
    }

    /**
     * Gets the index of the turtle's current color in the model. If the color doesn't exist in the
     * model, throws an exception
     * 
     * @return The index of the turtle's current color
     * @throws Exception If the color does not exist within the model's current palette, informs the
     *         user the color is not available anymore
     */
    public int getColorIndex () throws Exception {
        List<Color> list = myModel.getAvailableColors();
        for (int i = 0; i < list.size(); i++) {
            Color color = list.get(i);
            if (myColor.equals(color)) { return i; }
        }
        throw new ColorNotFound();
    }

    /**
     * @return The ID of the turtle
     */
    public int getID () {
        return myID;
    }

    /**
     * Gets the turtle's paths
     * 
     * @return A collection of paths that the turtle has created
     */
    protected Collection<Path> getPaths () {
        return myPaths;
    }

    /**
     * Gets the turtle's stamps
     * 
     * @return A collection of stamps that the turtle has created
     */
    protected Collection<Stamp> getStamps () {
        return myStamps;
    }

    /**
     * Creates a stamp for the turtle in its current x and y coordinates with its current
     * direction and shape and adds it to its collection of stamps
     */
    public void addStamp () {
        String shape = myModel.getAvailableShapes().get(myShapeIndex);
        double angleInDegrees = radiansToDegrees(myAngle);
        myStamps.add(new Stamp(myX, myY, angleInDegrees, shape));
    }

    /**
     * Creates a path for the turtle using an initial x and y location and a final x and y location
     * using the turtle's current pen size and color
     * 
     * @param x1 The initial x coordinate of the path
     * @param y1 The initial y coordinate of the path
     * @param x2 The final x coordinate of the path
     * @param y2 The final y coordinate of the path
     */
    private void addPath (double x1, double y1, double x2, double y2) {
        myPaths.add(new Path(x1, y1, x2, y2, myColor, myPenSize));
    }

    /**
     * Clears the turtle's collection of paths
     */
    protected void clearPaths () {
        myPaths.clear();
    }

    /**
     * Clears the turtle's collection of stamps
     */
    protected void clearStamps () {
        myStamps.clear();
    }

    /**
     * @return The turtle's current x coordinate
     */
    public double getX () {
        return myX;
    }

    /**
     * @return The turtle's current y coordinate
     */
    public double getY () {
        return myY;
    }

    /**
     * @return The turtle's current direction in degrees
     */
    public double getAngle () {
        return radiansToDegrees(myAngle);
    }

    /**
     * @return The turtle's current pen size
     */
    public int getPenSize () {
        return myPenSize;
    }

    /**
     * @return True if the turtle's pen is down, false if the turtle's pen is up
     */
    public boolean isDrawing () {
        return isDrawing;
    }

    /**
     * @return True if the turtle is visible, false if the turtle is invisible
     */
    public boolean isVisible () {
        return isVisible;
    }

    /**
     * @return True if the turtle is active, false if the turtle is inactive
     */
    public boolean isActive () {
        return isActive;
    }

    /**
     * Sets whether the turtle's pen is down or not
     * 
     * @param drawing Whether or not to put the turtle's pen down
     */
    public void setDrawing (boolean drawing) {
        isDrawing = drawing;
    }

    /**
     * Sets whether the turtle is visible or not
     * 
     * @param visible Whether or not to make the turtle visible
     */
    public void setVisible (boolean visible) {
        isVisible = visible;
    }

    /**
     * Sets whether the turtle is active or not
     * 
     * @param active Whether or not to make the turtle active
     */
    public void setActive (boolean active) {
        isActive = active;
    }

    /**
     * Sets the turtle's pen size
     * 
     * @param penSize The new pen size to set the turtle's pen size to
     */
    public void setPenSize (int penSize) {
        myPenSize = penSize;
    }

    /**
     * Moves the turtle forward the given number of pixels from its current location using its
     * current direction. If the turtle's pen is down, creates a new path on the turtle's route
     * 
     * @param pixels The number of pixels for the turtle to move forward
     * @return The number of pixels that the turtle has moved forward by
     */
    public double doRelativeMove (double pixels) {
        double oldX = myX, oldY = myY;
        myX += Math.cos(myAngle) * pixels;
        myY += Math.sin(myAngle) * pixels;
        if (isDrawing()) {
            addPath(oldX, oldY, myX, myY);
        }
        return pixels;
    }

    /**
     * Moves the turtle to an exact location on the screen
     * 
     * @param x The new intended x location for the turtle
     * @param y The new intended y location for the turtle
     * @return The distance in pixels that the turtle moved
     */
    public double doAbsoluteMove (double x, double y) {
        double oldX = myX, oldY = myY;
        myX = x;
        myY = y;
        if (isDrawing()) {
            addPath(oldX, oldY, myX, myY);
        }
        return distance(oldX, oldY, x, y);
    }

    /**
     * Rotates the turtle a given number of degrees
     * 
     * @param degrees The amount of rotation desired in degrees
     * @return The number of degrees that the turtle rotated
     */
    public double doRelativeRotate (double degrees) {
        double radians = degreesToRadians(degrees);
        myAngle = positiveMod(myAngle + radians, 2 * Math.PI); // maintains 0 to 2 PI
        return degrees;
    }

    /**
     * Rotates the turtle to a specific heading in degrees
     * 
     * @param degrees The heading in degrees that the turtle is intended to be rotated to
     * @return The number of degrees that the turtle rotated to reach the desired angle
     */
    public double doAbsoluteRotate (double degrees) {
        double deltaDegrees = degrees - radiansToDegrees(myAngle); // CCW change in degrees
        myAngle = positiveMod(degreesToRadians(degrees), 2 * Math.PI); // maintains 0 to 2 PI
        return deltaDegrees;
    }

    /**
     * Rotates the turtle towards a specified x and y location. If new direction has less of an
     * angle than the original direction, rotates the turtle clockwise. If new direction has a
     * greater angle than the original direction, rotates the turtle counterclockwise
     * 
     * @param x The x coordinate of the point to rotate the turtle towards
     * @param y The y coordinate of the point to rotate the turtle towards
     * @return The degrees that the turtle rotated
     */
    public double doRotateTowards (double x, double y) {
        double dx = x - myX, dy = y - myY;
        double newRadians = Math.atan2(dy, dx); // (-Math.pi, Math.pi]
        double oldRadians = myAngle;
        newRadians = positiveMod(newRadians, 2 * Math.PI); // (0, 2 PI]
        myAngle = newRadians;  // maintains 0 to 2 PI
        double deltaRadians = positiveMod(newRadians - oldRadians, 2 * Math.PI);
        return deltaRadians;
    }

    /**
     * Helper function to convert a given number of radians to degrees
     * 
     * @param radians Radians to convert
     * @return The number of degrees equivalent to the given radians
     */
    private static double radiansToDegrees (double radians) {
        return radians / Math.PI * 180;
    }

    /**
     * Helper function to convert a given number of degrees to radians
     * 
     * @param degrees Degrees to convert
     * @return The number of radians equivalent to the given degrees
     */
    private static double degreesToRadians (double degrees) {
        return degrees / 180 * Math.PI;
    }

    /**
     * Helper function to calculate a distance between two points
     * 
     * @param x1 The x coordinate of the initial point
     * @param y1 The y coordinate of the initial point
     * @param x2 The x coordinate of the final point
     * @param y2 The y coordinate of the final point
     * @return The distance between the two points given in pixels
     */
    private static double distance (double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    /**
     * Helper function to calculate the remainder of a given number divided by a given divisor. If
     * the remainder is negative, converts to a positive remainder by adding the divisor
     * 
     * @param num The number that is being divided
     * @param divisor The divisor to divide the number by
     * @return The positive remainder of the number divided by the divisor
     */
    private static double positiveMod (double num, double divisor) {
        double ret = num % divisor;
        return ret < 0 ? ret + divisor : ret;
    }
}
