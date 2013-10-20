package model;

import java.awt.Color;


public class Path {
    private double x1, x2, y1, y2;
    private Color myColor;
    private int myPenSize;

    public Path (double x1, double y1, double x2, double y2, Color color, int penSize) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        myColor = color;
        myPenSize = penSize;
    }

    public Color getColor () {
        return myColor;
    }

    public double getX1 () {
        return x1;
    }

    public double getX2 () {
        return x2;
    }

    public double getY1 () {
        return y1;
    }

    public double getY2 () {
        return y2;
    }

    public int getPenSize () {
        return myPenSize;
    }
}
