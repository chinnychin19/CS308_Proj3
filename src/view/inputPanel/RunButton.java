package view.inputPanel;

public class RunButton extends InputButtons {
    InputController myController;
    protected RunButton (InputController controller) {
        super("RUN",controller);
       
        
    }

    @Override
    protected void mouseClickAction () {
        myController.executeCommand();

    }

}
