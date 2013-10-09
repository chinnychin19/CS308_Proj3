package view.display;

import view.Constants;
import jgame.JGObject;
import jgame.platform.JGEngine;

/**
 * 
 * 
 * @author susanzhang93
 *
 */
public class Grid extends JGObject{
    private JGEngine engine;
    
    /**
     * Constructor for grid object
     * 
     * @param engine
     */
    public Grid (JGEngine engine) { 
        super("grid", true, 0.1, 0.1, 1, null);
        this.engine=engine;       
    }
    
    @Override
    public void paint(){
      //TODO: Find a way to clear this
        for (int i = 1; i < Constants.NUM_GRIDLINES; i++) {
            engine.drawLine(0, Constants.CANVAS_HEIGHT * i / Constants.NUM_GRIDLINES,
                     Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT * i / Constants.NUM_GRIDLINES);
        }

        for (int j = 1; j < Constants.NUM_GRIDLINES; j++) {
            engine.drawLine(Constants.CANVAS_WIDTH * j / Constants.NUM_GRIDLINES, 0,
                     Constants.CANVAS_WIDTH * j / Constants.NUM_GRIDLINES,
                     Constants.CANVAS_HEIGHT * 2);
        }
    }

}
