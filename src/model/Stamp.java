package model;

/**
 * 
 * Stamp class that keeps track of a stamp created by a turtle. Keeps track of the location of the
 * stamp, the direction of the stamp in degrees, and the shape used by the stamp
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class Stamp {
    private double myX, myY, myAngle;
    private String myShape;

    /**
     * Constructor for a stamp object. Initializes the x and y coordinates of the stamp, the
     * direction of the stamp in degrees and the shape used by the stamp
     * 
     * @param x The x coordinate of the stamp
     * @param y The y coordinate of the stamp
     * @param angle The direction of the stamp in degrees
     * @param shape The shape used by the stamp
     */
    protected Stamp (double x, double y, double angle, String shape) {
        myX = x;
        myY = y;
        myAngle = angle;
        myShape = shape;
    }

    /**
     * @return The x coordinate of the stamp
     */
    public int getX () {
        return (int) myX;
    }

    /**
     * @return The y coordinate of the stamp
     */
    public int getY () {
        return (int) myY;
    }

    /**
     * @return The direction of the stamp in degrees
     */
    public int getAngle () {
        return (int) myAngle;
    }

    /**
     * @return The shape used by the stamp
     */
    public String getShape () {
        return myShape;
    }

    /**
     * 
     */
    // TODO: Javadoc for clone
    public Stamp clone () {
        Stamp newStamp = new Stamp(myX, myY, myAngle, myShape);
        return newStamp;
    }
}
