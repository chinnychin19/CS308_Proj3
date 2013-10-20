package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import model.instruction.error.ColorNotFound;


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
        myPenSize = m.getPenSize();
    }

    public int getShapeIndex () {
        return myShapeIndex;
    }

    public void setShape (int shapeIndex) {
        myShapeIndex = shapeIndex;
    }

    public Color getColor () {
        return myColor;
    }

    public void setColor (Color color) {
        myColor = color;
    }

    public int getColorIndex () throws Exception {
        List<Color> list = myModel.getAvailableColors();
        for (int i = 0; i < list.size(); i++) {
            Color color = list.get(i);
            if (myColor.equals(color)) { return i; }
        }
        throw new ColorNotFound();
    }

    public int getID () {
        return myID;
    }

    protected Collection<Path> getPaths () {
        return myPaths;
    }

    protected Collection<Stamp> getStamps () {
        return myStamps;
    }

    public void addStamp () {
        myStamps.add(new Stamp(myX, myY, myAngle, myShapeIndex));
    }

    private void addPath (double x1, double y1, double x2, double y2) {
        myPaths.add(new Path(x1, y1, x2, y2, myColor, myPenSize));
    }

    protected void clearPaths () {
        myPaths.clear();
    }

    protected void clearStamps () {
        myStamps.clear();
    }

    public double getX () {
        return myX;
    }

    public double getY () {
        return myY;
    }

    public double getAngle () {
        return radiansToDegrees(myAngle);
    }

    public int getPenSize () {
        return myPenSize;
    }

    public boolean isDrawing () {
        return isDrawing;
    }

    public boolean isVisible () {
        return isVisible;
    }

    public boolean isActive () {
        return isActive;
    }

    public void setDrawing (boolean drawing) {
        isDrawing = drawing;
    }

    public void setVisible (boolean visible) {
        isVisible = visible;
    }

    public void setActive (boolean active) {
        isActive = active;
    }

    public void setPenSize (int penSize) {
        myPenSize = penSize;
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
        myAngle = positiveMod(myAngle + radians, 2 * Math.PI); // maintains 0 to 2 PI
        return degrees;
    }

    public double doAbsoluteRotate (double degrees) {
        double deltaDegrees = degrees - radiansToDegrees(myAngle); // CCW change in degrees
        myAngle = positiveMod(degreesToRadians(degrees), 2 * Math.PI); // maintains 0 to 2 PI
        return deltaDegrees;
    }

    public double doRotateTowards (double x, double y) {
        double dx = x - myX, dy = y - myY;
        double newRadians = Math.atan2(dy, dx); // (-Math.pi, Math.pi]
        double oldRadians = myAngle;
        newRadians = positiveMod(newRadians, 2 * Math.PI); // (0, 2 PI]
        myAngle = newRadians;  // maintains 0 to 2 PI
        double deltaRadians = positiveMod(newRadians - oldRadians, 2 * Math.PI);
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

    private static double positiveMod (double num, double divisor) {
        double ret = num % divisor;
        return ret < 0 ? ret + divisor : ret;
    }
}
