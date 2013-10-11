package view;

import jgame.JGColor;


/**
 * Class that stores constants for the view
 * 
 * @author susanzhang93
 * 
 */
public interface Constants {
    public static final int FRAMES_PER_SECOND = 60;
    public static final int GUI_WIDTH = 1000;
    public static final int GUI_HEIGHT = 650;
    public static final int DISPLAY_HEIGHT = 100;
    public static final int DISPLAY_WIDTH = 300;

    public static final int TURTLE_OFFSET = 25;

    public static final int GRID_CID = 2;
    public static final int TURTLE_CID = 0;

    public static final int CANVAS_WIDTH = 600;
    public static final int CANVAS_HEIGHT = 600;
    public static final JGColor CANVAS_COLOR = JGColor.blue;

    public static final int Y_TILES = 50;
    public static final int X_TILES = 50;
    public static final int TILE_HEIGHT = 12;

    public static final JGColor GRIDLINE_COLOR = JGColor.black;
    public static final int GRIDLINE_THICKNESS = 2;
    public static final int NUM_GRIDLINES = 20;

    public static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    public static final String USER_DIR = "user.dir";
    public static final int FIELD_SIZE = 30;

}
