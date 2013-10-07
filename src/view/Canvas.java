package view;
 
import jgame.JGColor;
import jgame.platform.JGEngine;


public class Canvas extends JGEngine {

    public Canvas () {
        initEngineComponent(100, 100);
    }

    @Override
    public void initCanvas () {
        setCanvasSettings(1, 1, 100, 100, null, JGColor.red, null);

    }

    @Override
    public void initGame () {
        setFrameRate(60, 2);

    }

}
