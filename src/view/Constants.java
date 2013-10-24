package view;

import javax.swing.JFileChooser;
import jgame.JGColor;


/**
 * Class that stores constants for the view
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 * 
 */
public interface Constants {
    public static final int FRAMES_PER_SECOND = 20;
    public static final int GUI_WIDTH = 900;
    public static final int GUI_HEIGHT = 755;
    public static final int DISPLAY_HEIGHT = 100;
    public static final int DISPLAY_WIDTH = 300;

    public static final int LINE_OFFSET = 13;
    public static final int TURTLE_OFFSET = 25;
    public static final int FONT_SIZE = 12;
    public static final int MARGIN = 5;
    public static final int SHAPE_NAME_LENGTH = 7;
    public static final String IMAGE_NAME = "turtleGif";
    public static final String IMAGE_FULL_NAME = "Turtle1_1.gif";
    public static final String DEFAULT_IMAGE_SUFFIX = "_1.gif";
    public static final String IMAGE_SUFFIX = ".gif";

    public static final int Q1 = 135;
    public static final int Q2 = 225;
    public static final int Q3 = 315;
    public static final int Q4 = 45;
    public static final int INDEX_1 = 1;
    public static final int INDEX_2 = 2;
    public static final int INDEX_3 = 3;
    public static final int INDEX_4 = 4;
    public static final int TURTLE_CID = 0;

    public static final double ERROR_MESSAGE_OFFSET = 0.95;

    public static final int CANVAS_WIDTH = 600;
    public static final int CANVAS_HEIGHT = 600;
    public static final JGColor CANVAS_COLOR = JGColor.black;
    public static final JGColor PEN_COLOR = JGColor.red;
    public static final String INITIAL_ERROR = "";

    public static final int Y_TILES = 50;
    public static final int X_TILES = 50;
    public static final int TILE_HEIGHT = 12;

    public static final int NUM_GRIDLINES = 20;
    public static String[] TURTLE_OPTIONS = { "Turtle1.gif", "Turtle2.gif", "Turtle3.gif" };

    public static final String GRID = "Grid";
    public static final String HIGHLIGHT = "Highlight";
    public static final String TURTLE_STATUS = "Turtle Status";

    public static final String CHANGE_IMAGE_BUTTON = "Change Image";
    public static final String CHANGE_BG_BUTTON = "Change BG Color";
    public static final String CHANGE_PEN_BUTTON = "Change Pen Color";
    public static final String CHOOSE_COLOR_INDEX = "Choose a color index. \nThe current index is ";
    public static final String CHOOSE_IMAGE = "Choose a turtle image. \nThe current image is ";
    public static final String IMAGE_CHOOSER_TITLE = "Turtle Image Chooser";
    public static final String BG_CHOOSER_TITLE = "Background color chooser";
    public static final String PEN_CHOOSER_TITLE = "Pen color chooser";

    public static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    public static final String USER_DIR = "user.dir";
    public static final int FIELD_SIZE = 30;

    public static final int TEXTBOX_ROWS = 5;

    public static final String CREATE_NEW_WORK_SPACE_OPTION = "Create New Workspace";
    public static final String WORK_SPACE_MESSAGE = "Choose a Workspace";
    public static final String WORK_SPACE_TITLE = "Workspace";
    public static final String CHANGED_WORKSPACE_MESSAGE =
            "Choose a Workspace \n You are currently in Workspace ";
    public static final String NEW_WORKSPACE_MESSAGE =
            "A new Workspace has been created. You are now in Workspace ";

    public static final String WRONG_PREF_FILE_MESSAGE = "Not a preference file";
    public static final String BACKGROUND_KEYWORD = "background";
    public static final String PEN_KEYWORD = "pen";
    public static final String IMAGE_KEYWORD = "image";

    public static final String CHANGE_WORKSPACE_NAME = "Change Workspace";
    public static final String WORKSPACE_PREFERENCES_NAME = "Workspace Preferences";
    public static final String SAVE_WORKSPACE_PREFERENCES = "Save Workspace Preferences";
    public static final String LOAD_WORKSPACE_PREFERENCES = "Load Workspace Preferences";
    public static final int COLOR_LINE_LENGTH = 5;

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
    public static final String FILE_MENU_NAME = "File";
    public static final String OPEN_MENU_NAME = "Open";
    public static final String SAVE_MENU_NAME = "Save";
    public static final String HELP_URL_PART_2 =
            "http://www.cs.duke.edu/courses/compsci308/current/assign/03_slogo/commands.php";
    public static final String HELP_URL_PART_3 =
            "http://www.cs.duke.edu/courses/compsci308/current/assign/03_slogo/commands2.php";
    public static final String HELP_MENU_2 = "Part 2 Help";
    public static final String HELP_MENU_3 = "Part 3 Help";
    public static final String HELP_MENU_NAME = "Help";
    public static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir"));

    public static final double DELTA = 0.01;
}
