package view;

import model.Model;
import view.display.ViewUpdater;
import view.input.RunButton;
import view.input.Textbox;
import view.sidebar.SideBar;


public class View {
    protected ViewUpdater myViewUpdater;
    protected RunButton myRunButton;
    protected Textbox myTextbox;
    protected SideBar mySidebar;
    protected Model myModel;

    public View () {

    }
    
    protected void sendInput(){
        //when runButton is activated
        //input is sent to model
        String input = "";
        myModel.parseInput(input);  
        //Clear textbox    
    }
    protected void executeInput(){
        myViewUpdater.displayOutput();
        mySidebar.update();
    }
    
}
