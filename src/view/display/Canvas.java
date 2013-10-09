package view.display;

import view.Constants;
import jgame.JGColor;
import jgame.JGPoint;
import jgame.platform.JGEngine;



public class Canvas extends JGEngine {
    private TurtleSprite turtle = null;
    
    public static void main(String [] args){
        new Canvas(new JGPoint(1000, 1000)); 
    }
    
//    public Canvas () {
//        initEngineComponent(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
//    }
    
   
    public Canvas(JGPoint size){
        initEngine(size.x, size.y);
    }
    
    public Canvas() {
        initEngineApplet();
    }
    

    @Override
    public void initCanvas () {
        setCanvasSettings(Constants.X_TILES, Constants.Y_TILES, Constants.TILE_HEIGHT, Constants.TILE_HEIGHT, null , Constants.CANVAS_COLOR, null);
    }

    @Override
    public void initGame () {
        setFrameRate(60, 2);
        defineImage("turtleGif", "-", 0, "Turtle1.gif", "-", 0, 0, 50, 50);
        //Why these x and y?
        turtle = new TurtleSprite(this, 0, 0, 1);
        paintFrame();
        
    }
    
    @Override
    public void paintFrame(){
        super.paintFrame();
        //moveTurtle(200, 200);
        //moveTurtle(100, -100);
        //drawGrid();
    }
    
    public void moveTurtle(double x, double y){
        drawLine(turtle.x, turtle.y, turtle.x+x, turtle.y+y);
        turtle.setPos(x, y);
    }
    
    /**
     * Method that draws grid
     */
    public void drawGrid(){
       for (int i=1; i < Constants.NUM_GRIDLINES; i++){
           drawLine(0, Constants.CANVAS_HEIGHT*2*i/Constants.NUM_GRIDLINES, Constants.CANVAS_WIDTH*2, Constants.CANVAS_HEIGHT*2*i/Constants.NUM_GRIDLINES);
       }
       
       for (int j=1; j < Constants.NUM_GRIDLINES; j++){
           drawLine(Constants.CANVAS_WIDTH*2*j/Constants.NUM_GRIDLINES, 0, Constants.CANVAS_WIDTH*2*j/Constants.NUM_GRIDLINES, Constants.CANVAS_HEIGHT*2);
       }
         
    }

}
