package view.inputPanel;

@SuppressWarnings("serial")
public class RunButton extends InputButtons {
   
    protected RunButton (InputController controller) {
        super("RUN",controller);
       
        
    }

    @Override
    protected void mouseClickAction () {
        myController.executeCommand();

    }

}