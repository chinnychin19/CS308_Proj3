package view;

import jgame.JGColor;


/**
 * Class that stores constants for the view
 * 
 * @author susanzhang93
 * 
 */
public interface Constants {
    public static final int FRAMES_PER_SECOND = 20;
    public static final int GUI_WIDTH = 900;
    public static final int GUI_HEIGHT = 758;
    public static final int DISPLAY_HEIGHT = 100;
    public static final int DISPLAY_WIDTH = 300;
    
    public static final int LINE_OFFSET = 13;
    public static final int TURTLE_OFFSET = 25;
    public static final int FONT_SIZE = 12;
    public static final int MARGIN = 5;
    public static final int SHAPE_NAME_LENGTH = 7;
    public static final String IMAGE_NAME = "turtleGif";
   

    public static final int GRID_CID = 2;
    public static final int TURTLE_CID = 0;
    public static final int STAMP_CID = -2;

    public static final double ERROR_MESSAGE_OFFSET = 0.95;

    public static final int CANVAS_WIDTH = 600;
    public static final int CANVAS_HEIGHT = 600;
    public static final JGColor CANVAS_COLOR = JGColor.black;

    public static final int Y_TILES = 50;
    public static final int X_TILES = 50;
    public static final int TILE_HEIGHT = 12;

    public static final JGColor GRIDLINE_COLOR = JGColor.black;
    public static final int GRIDLINE_THICKNESS = 2;
    public static final int NUM_GRIDLINES = 20;
    public static String[] TURTLE_OPTIONS = { "Turtle1.gif", "Turtle2.gif", "Turtle3.gif" };
    
    public static final String CHOOSE_IMAGE = "Choose a turtle image. \nThe current image is ";
    public static final String IMAGE_CHOOSER_TITLE = "Turtle Image Chooser";

    public static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    public static final String USER_DIR = "user.dir";
    public static final int FIELD_SIZE = 30;

    public static final int TEXTBOX_ROWS = 5;

    public static final String CREATE_NEW_WORK_SPACE_OPTION = "Create New WorkSpace";
    public static final String WORK_SPACE_MESSAGE = "Choose a Work Space";
    public static final String WORK_SPACE_TITLE = "Work Space";
    public static final String CHANGED_WORKSPACE_MESSAGE =
            "Choose a Work Space \n You are currently in Work Space ";
    public static final String NEW_WORKSPACE_MESSAGE =
            "A new work space has been created. You are now in work space ";

    public static final String WRONG_PREF_FILE_MESSAGE = "Not a preference file";
    public static final String NOT_XML_MESSAGE = "Not an XML file";

    public static final String CHANGE_WORKSPACE_NAME = "Change Workspace";
    public static final String WORKSPACE_PREFERENCES_NAME = "Workspace Preferences";
    public static final String SAVE_WORKSPACE_PREFERENCES = "Save Workspace Preferences";
    public static final String LOAD_WORKSPACE_PREFERENCES = "Load Workspace Preferences";

    public static final String SELECT_A_VARIABLE_MESSAGE = "Select a variable";
    public static final int TEXTBOX_COLUMNS = 15;
    public static final String EDIT_BUTTON_NAME = "Edit";

    public static final String VARIABLE_INPUT_DISPLAY_NAME = "variable";
    public static final String COMMAND_INPUT_DISPLAY_NAME = "command";
    public static final String HISTORY_INPUT_DISPLAY_NAME = "history";

    public static final int INPUT_DISPLAY_HEIGHT = 100;
    public static final int INPUT_DISPLAY_WIDTH = 300;
    public static final int INPUT_PANEL_ROWS = 1;
    public static final int INPUT_PANEL_COLUMNS = 4;
}
